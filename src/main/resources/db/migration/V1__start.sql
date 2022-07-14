begin;
--https://quarkus.io/guides/flyway#developing-with-flyway

create table orgs(
    id bigserial,
    name varchar,
    crm_id varchar,
    billing_id varchar,
    guid uuid
)

create table users(
  id bigserial,
  guid uuid,
  first_name varchar(20),
  last_name varchar(20),
  active bool,
  org_id bigint references orgs(id),
  created_at timestamp default now(),
  email varchar
);

create table files(
    id bigserial,
    name varchar,
    status varchar,
    org_id bigint references orgs(id),
    created_at timestamp default now(),
    metadata json
)

create table documents(
    id bigserial
    file_id bigint references files(id)
    created_at timestamp default now(),
    name varchar
    url varchar,
)

end;