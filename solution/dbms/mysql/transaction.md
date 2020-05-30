
#ACID实现
- 通过redo log~~~~实现事务的AD
> redo log buffer ,redo log file
> 通过write redo log before commit 
> **顺序读写**
> 通过 fsync 实现持久化
- 通过undo log 实现事务的C
> 帮助实现**MVCC和事务回滚**
> 随机读写

##BINLOG
> - binlog 由mysql产生，而redo,undolog由innodb产生
> - binlog 记录了对应的sql, log记录了对页的修改
> - binlog 在事务提交的时写入 