import analysis.CommandParser;
import command.CommandHandle;
import config.Command;
import util.CommandHandleHolder;
import util.InputScanner;

/**
 * Created by null on 2016/10/9.
 *
 * 测试用例
 *
 create table Student(name string, id integer, grade integer)

 insert into Student values (香港记者,23,23)
 insert into Student values (总想,21,66)
 insert into Student values (搞个,11,34)
 insert into Student values (大新闻,27,12)
 insert into Student values (西方记者,11,87)
 insert into Student values (不知道,15,37)
 insert into Student values (高到,31,57)
 insert into Student values (哪儿里去了,13,53)
 insert into Student values (跑的特别快,76,54)
 insert into Student values (谈笑风生,71,24)

 insert into Student (name,grade) values (老王,23)

 select * from Student
 select * from Student where id=11
 select name,grade from Student
 select name,grade from Student where id=11 or grade=66


 alter add Student (tail integer, sex string)
 alter drop Student (tail, sex)

 update Student name=小李,id=55
 update Student name=单独更改 where grade=23
 update Student name=测试OR where grade=23 OR id=11
 update Student name=测试AND where grade=23 AND id=11

 delete from Student where id=23 and grade=23
 delete from Student

 drop table Student

 create unique index student id
 create index student name

 show index student

 delete index student id

 drop index student id

 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class Main {
    public static void main(String[] args){
        InputScanner inputScanner = InputScanner.getSingle();
        while (inputScanner.hasNext()){
            String command = inputScanner.getInput();
            Command parserResult = CommandParser.parser(command);
            CommandHandle handle = CommandHandleHolder.getHandle(parserResult);
            try {
                handle.handle(command);
                System.out.println("执行完毕");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
