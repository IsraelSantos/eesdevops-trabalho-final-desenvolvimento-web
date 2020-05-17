package br.uece.eesdevops.amazingmovies;

import java.io.IOException;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;

class EmbeddedDatabase {

    private final EmbeddedPostgres embeddedPostgres;

    EmbeddedDatabase() throws IOException {
        this.embeddedPostgres = EmbeddedPostgres.builder().setPort(5433).start();
    }

    void stopServer() throws IOException {
        embeddedPostgres.close();
    }

}
