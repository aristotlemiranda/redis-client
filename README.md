### Troubleshooting
* To fix NOPERM client|info issue. Try to add permission using default user to the designated user. 
  ``` 
  127.0.0.1:6379> AUTH foobared
  OK
  127.0.0.1:6379> ACL SETUSER appuser +@all -@admin +info +client
  OK
  127.0.0.1:6379> SAVE
  6067:M 30 Apr 2024 00:48:39.866 * DB saved on disk
  OK
  127.0.0.1:6379>
 ```

java -XshowSettings:properties -version
