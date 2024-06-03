create table bindings(
  id uuid primary key not null,
  state varchar(20) not null,
  state_date timestamptz not null,
  header_code bytea,
  package_name varchar(256) not null,
  clang_flags bytea
);

create table leases(
  binding_id uuid not null,
  worker_id uuid not null,
  checked_in_at timestamptz not null
);

create table generated_code(
  binding_id uuid not null,
  scala_code bytea not null,
  glue_code bytea
);

create table errors(
  binding_id uuid not null,
  message text not null,
  diagnostics bytea 
);
