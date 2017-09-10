# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table sieve_query (
  primes_count                  integer not null,
  start                         integer not null,
  end                           integer not null,
  elapsed_time                  bigint not null,
  created_at                    bigint not null,
  sieve_type                    varchar(255)
);


# --- !Downs

drop table if exists sieve_query;

