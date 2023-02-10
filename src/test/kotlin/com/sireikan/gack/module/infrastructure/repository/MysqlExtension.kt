package com.sireikan.gack.module.infrastructure.repository

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

class MysqlExtension : BeforeAllCallback, AfterAllCallback {

    lateinit var mysql: MySQLContainer<*>

    override fun beforeAll(context: ExtensionContext?) {
        this.mysql = MySQLContainer(DockerImageName.parse("mysql:8.0"))
            .withUsername("user")
            .withPassword("password")
            .withDatabaseName("sample")
            .withExposedPorts(3306)

        this.mysql.start()
        val jdbcUrl: String = String.format("jdbc:mysql://localhost:%d/sample?autoReconnect=true", this.mysql.firstMappedPort);
        System.setProperty("spring.datasource.url", jdbcUrl);
        System.setProperty("spring.datasource.username", "user");
        System.setProperty("spring.datasource.password", "password");
    }

    override fun afterAll(context: ExtensionContext?) {
        this.mysql.stop()
    }
}
