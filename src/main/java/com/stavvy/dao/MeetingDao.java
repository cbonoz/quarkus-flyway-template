package com.stavvy.dao;

import com.stavvy.models.AppUser;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface MeetingDao {

    @SqlUpdate("insert into users (name) values (?)")
    void insert(String name);

    @SqlQuery("select * from users where id=:id limit 1")
    Optional<AppUser> find(@Bind("id") long id);

    @SqlQuery("select * from users")
    List<AppUser> getUsers();

    @SqlQuery("select * from users where orgId=:orgId")
    List<AppUser> getOrgUsers(@Bind("orgId") long orgId);
}