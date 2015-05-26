# Hibernate Module

## TODO
* Update hibernate version. Hikari is not working because of version.

## Notes
* All entities must have @Id field.

## Hints
### Hibernate with HikariCP
```
"database": {
    "driver": "com.mysql.jdbc.Driver",
    "url": "jdbc:mysql://localhost:3306/mud",
    "user": "root",
    "password": "",
    "properties":{
      "hibernate.dialect" :"org.hibernate.dialect.MySQL5InnoDBDialect",
      "hibernate.hbm2ddl.auto": "update",
      "hibernate.hikari.minimumPoolSize":1,
      "hibernate.hikari.maximumPoolSize":10,
      "hibernate.hikari.idleTimeout":30000,
      "hibernate.connection.provider_class":"com.zaxxer.hikari.hibernate.HikariConnectionProvider"
    }
}
```