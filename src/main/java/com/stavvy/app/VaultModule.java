package com.stavvy.app;

import com.hubspot.rosetta.jdbi3.RosettaRowMapperFactory;
import com.stavvy.dao.MeetingDao;
import com.stavvy.dao.UserDao;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.transaction.SerializableTransactionRunner;
import org.jdbi.v3.jackson2.Jackson2Plugin;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.ses.SesClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.ws.rs.Produces;

@ApplicationScoped
public class VaultModule {

    @Inject
    DataSource dataSource;

    @ConfigProperty(name = "aws.profile")
    String AWS_PROFILE;

    @Produces
    @ApplicationScoped
    public Jdbi providesJdbi() {
        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new PostgresPlugin())
                .installPlugin(new SqlObjectPlugin())
                .installPlugin(new Jackson2Plugin())
                .setTransactionHandler(new SerializableTransactionRunner());
        jdbi.registerRowMapper(new RosettaRowMapperFactory());

        return jdbi;
    }

    @Produces
    @ApplicationScoped
    public ProfileCredentialsProvider profileCredentialsProvider() {
        return ProfileCredentialsProvider.create(AWS_PROFILE);
    }

    @Produces
    @ApplicationScoped
    public SesClient ses(ProfileCredentialsProvider provider) {
        return SesClient.builder().credentialsProvider(provider).region(Region.US_EAST_1).build();
    }

    @Produces
    @ApplicationScoped
    public S3Client s3(ProfileCredentialsProvider provider) {
        return S3Client.builder().credentialsProvider(provider).build();
    }

    @Produces
    @ApplicationScoped
    public UserDao userDao(final Jdbi jdbi) {
        return jdbi.onDemand(UserDao.class);
    }

    @Produces
    @ApplicationScoped
    public MeetingDao meetingDao(final Jdbi jdbi) {
        return jdbi.onDemand(MeetingDao.class);
    }
}
