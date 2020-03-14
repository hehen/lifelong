## 资料
[bilibili](https://www.bilibili.com/video/av62800055?p=4)
[spring](https://spring.io/guides/gs/serving-web-content/)
[github](https://github.com/hehen/lifelong)
[bootstrap](https://v3.bootcss.com)
[github oauth app](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)
[okhttp](https://square.github.io/okhttp/)
[maven仓库](https://mvnrepository.com/)
[flywaydb](https://flywaydb.org/getstarted/firststeps/maven)

## 脚本
```sql
create table USER
(
    ID           INT auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHARACTER(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);

```


```bash
mvn flyway:migrate
```
