package bindgen.web.frontend

import com.raquo.laminar.api.L.*
import bindgen.web.domain.*
import bindgen.web.domain.BindingStatus.*
import com.raquo.waypoint.*
import com.raquo.airstream.web.WebStorageVar
import java.util.UUID
import scala.scalajs.js.JSON
import scala.util.Success
import scala.util.Try
import scalajs.js

class RecentBindingsManager private (
    state: Var[List[(String, UUID)]],
    maxLength: Int
):
  def signal = state.signal
  def add(name: String, id: UUID) =
    state.update { lst =>
      (name, id) :: lst
        .filterNot(b => b._2 == id || b._1 == name)
        .take(maxLength - 1)
    }

  def updater = Observer((nameId: (String, UUID)) => add(nameId._1, nameId._2))
end RecentBindingsManager

object RecentBindingsManager:
  def apply(maxLength: Int, owner: Owner) =
    val recentBindings = WebStorageVar
      .localStorage("bindgen-web-recent-bindings", Some(owner))
      .withCodec[List[(String, UUID)]](
        encode = lst =>
          JSON.stringify(
            js.Array
              .apply(lst*)
              .map: (name, id) =>
                js.Array(name, id.toString)
          ),
        decode = str =>
          Try:
            JSON
              .parse(str)
              .asInstanceOf[js.Array[js.Array[String]]]
              .toList
              .map: arr =>
                (arr(0), UUID.fromString(arr(1)))
        ,
        default = Success(Nil)
      )

    new RecentBindingsManager(recentBindings, maxLength)
  end apply
end RecentBindingsManager
