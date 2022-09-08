# PL/Java Getting Started

Is Java your primary programming language? Do you need to create stored procedures for a database? Do you want to develop those procedures in Java?

If YES, then follow the guide below!

## Postgres

For Postgres, you can use the PL/Java extension. Below are few good resources to get to know the module:
* https://tada.github.io/pljava/
* https://flylib.com/books/en/2.290.1/pl_javawriting_stored_procedures_in_java.html

### Install and Start Postgres

You need to build PL/Java from sources. This requires you to get a Postgres instance running on your host machine.

1. Download and install Postgres. For Ubuntu, use the `apt` manager and install the following packages:
    https://tada.github.io/pljava/build/ubuntu.html

2. Start a Postgres instance. Depends on your OS:
    https://tableplus.com/blog/2018/10/how-to-start-stop-restart-postgresql-server.html
  
    For Ubuntu you can use these commands to start and stop the service:
    ```shell
    sudo service postgresql start
    sudo service postgresql stop
    ```
3. Connect to Postgres making sure it's working:
    ```shell
    #For Ubuntu
    sudo -u postgres psql
    ```

### Buld and Install PL/Java

1. Clone:
    ```shell
    git clone https://github.com/tada/pljava.git
    ```
2. Build:
    ```shell
    mvn clean install
    ```
3. Install into Postgres:
    ```shell
    java -jar pljava-packaging/target/pljava-pgX.jar
    ```
4. Create the extension:
    ```sql
    #Connect to the `postgres` database with psql
    sudo -u postgres psql -d postgres
  
    #Specify a libjli location (libjvm for other operating systems)
    SET pljava.libjvm_location TO '/home/dmagda/.sdkman/candidates/java/current/lib/server/libjvm.so';
  
    #Make the setting persistent, so that it applies to all connections to the postgres database
    ALTER DATABASE postgres SET pljava.libjvm_location FROM CURRENT;
    
    #Install the extension
    CREATE EXTENSION pljava;
    ```
5. Make sure you see pljava in the extensions list:
    ```sql
    SELECT * FROM pg_extension;
    ```
### Run Examples Shipped with PL/Java

Test the extension by running examples that are bundled with the pljava:
https://tada.github.io/pljava/examples/examples.html

1. Load the examples into Postgres:
    ```sql
    SELECT sqlj.install_jar(
    'file:'
    '/home/dmagda/pljava/pljava-examples/target/pljava-examples-2-SNAPSHOT.jar',
    'examples', true);
    ```
2. Make sure the `javatest` schema appeared on `search_path`:
    ```sql
    SHOW search_path;
    ```
3. Check that the Java classpath for that schema includes the ID for this jar:
    ```sql
    SELECT sqlj.get_classpath('javatest');
    ```
4. If it doesn't, set the classpath:
    ```sql
    SELECT sqlj.set_classpath('javatest', 'examples');
    ```
5. Run a sample example function:
    ```sql
    SELECT javatest.java_addone(5);
    
         java_addone 
    -------------
               6
    (1 row)
    ```
 ### Install and Run Custom Function
 
The `CustomFunctionsPostgres` class includes functions that you can add to Postgres and execute via the SQL interface.

1. Build the project:
    ```shell
    mvn clean package
    ```
2. (Optional) curious to see what is in the pljava.ddr file?
    ```sql
    unzip -c target/java-database-functions-1.0.jar pljava.ddr

    #The ouput might be as follows
        Archive:  target/java-database-functions-1.0.jar
    inflating: pljava.ddr              
    SQLActions[]={
    "BEGIN INSTALL
    BEGIN PostgreSQL
    CREATE OR REPLACE FUNCTION postgresSayHi()
            RETURNS pg_catalog.void
            LANGUAGE java VOLATILE
            AS e'void=com.yugabyte.examples.CustomFunctionsPostgres.postgresSayHi()'
    END PostgreSQL;
    BEGIN PostgreSQL
    COMMENT ON FUNCTION postgresSayHi()
    IS e'The Hello World function that returns information about the runtime.'
    END PostgreSQL;
    END INSTALL",
    "BEGIN REMOVE
    BEGIN PostgreSQL
    DROP FUNCTION postgresSayHi()
    END PostgreSQL;
    END REMOVE"
    }
    ```
 
 ### Update Custom Function
 
 TBD