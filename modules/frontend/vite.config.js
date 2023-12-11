import { defineConfig } from "vite";
import scalaJSPlugin from "@scala-js/vite-plugin-scalajs";

export default defineConfig({
  plugins: [scalaJSPlugin({
    projectID: 'frontend',
    cwd: '../../'
  })],
  server: {
    proxy: {
      '/api': {
        target: "http://localhost:9999",
        changeOrigin: true
      }
    }
  }

});
