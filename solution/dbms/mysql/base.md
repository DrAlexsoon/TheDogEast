#线程模型
#INNODB
##内存模型[source][-]
###In-Memory
 - Buffer Pool
 - Change Buffer
 - Adaptive Hash Index
 - Log Buffer
###On-Disk
- Tables
- Indexes
- Tablespaces
- Double write Buffer
> 将脏页刷新到buffer,memcpy + fsync 刷新到磁盘 
- Redo Log
> write log before transaction AD
- Undo Logs
> helper mvcc and transaction C
###Command
> - show variables; 配置查询
> - show variables like 'max_connections';  最大连接数
> - show global status like 'max_used_connections'; 相应连接数
> - show global status like 'Thread%'; 连接数 show variables like 'Thread%'

[-] https://dev.mysql.com/doc/refman/8.0/en/innodb-architecture.html
