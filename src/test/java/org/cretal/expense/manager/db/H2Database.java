package org.cretal.expense.manager.db;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Comparator;

public class H2Database implements Closeable {
    private final Path dbPath;
    private final String jdbcUrl;

    public H2Database(final Path dbPath, final String ddlResourcePath) {
        this.dbPath = dbPath;
        this.jdbcUrl = "jdbc:h2:" + dbPath;
        Connection conn = this.getConnection();
        
//
//        try {
//            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
//            Liquibase liquibase = new Liquibase(ddlResourcePath, new ClassLoaderResourceAccessor(), database);
//            liquibase.update(new Contexts(), new LabelExpression());
//        } catch (LiquibaseException var6) {
//            throw new RuntimeException(var6);
//        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(this.jdbcUrl);
        } catch (SQLException var2) {
            throw new RuntimeException("Cannot open connection to: " + this.jdbcUrl, var2);
        }
    }

    public void close() {
        if (Files.exists(this.dbPath, new LinkOption[0])) {
            deleteAll(this.dbPath);
        }

    }

    public static boolean deleteAll(final Path dir) {
        if (dir != null) {
            if (!Files.isDirectory(dir)) {
                return false;
            }
            try {
                //https://stackoverflow.com/questions/35988192/java-nio-most-concise-recursive-directory-delete
                return Files.walk(dir, FileVisitOption.FOLLOW_LINKS)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .allMatch(File::delete);

            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return false;
    }
}
