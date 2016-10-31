import analysis.CommandParser;
import bean.User;
import command.CommandHandle;
import config.Command;
import util.CommandHandleHolder;
import util.InputScanner;
import util.UserUtil;

/**
 * Created by null on 2016/10/9.
 *
 * 测试用例
 *
 *
 *
 create table Student(name string, id integer)

 insert into Student values (香港记者,1)
 insert into Student values (总想,2)
 insert into Student values (搞个,3)
 insert into Student values (大新闻,4)
 insert into Student values (西方记者,5)
 insert into Student values (不知道,6)
 insert into Student values (高到,7)
 insert into Student values (哪儿里去了,8)
 insert into Student values (跑的特别快,9)
 insert into Student values (谈笑风生,10)

 insert into Student (name) values (老王)

 select * from Student
 select * from Student where id=11
 select name,grade from Student
 select name,grade from Student where id=11 or grade=66

 alter add Student (tail integer, sex string)
 alter drop Student (tail, sex)

 update Student name=小李,id=55
 update Student name=单独更改 where id=23
 update Student name=测试OR where id=23 OR id=11
 update Student name=测试AND where id=23 AND name=香港记者

 delete from Student where id=23 and grade=23
 delete from Student

 drop table Student

 create unique index student id
 create index student name

 show index student

 delete index student id

 drop index student id


 create table transcript(subject string, grade integer, id integer)
 insert into transcript values (语文,12,1)
 insert into transcript values (数学,54,2)
 insert into transcript values (英语,23,3)
 insert into transcript values (C语言,76,4)
 insert into transcript values (数据结构,11,5)
 insert into transcript values (数据结构,65,6)
 insert into transcript values (数据结构,87,7)

 create table subject(subject string, teacher string)
 insert into subject values (语文,语文老师)
 insert into subject values (数学,数学老师)
 insert into subject values (英语,英语老师)

 select * from student,transcript,subject where student.id=transcript.id and transcript.subject=subject.subject



 create table test(id string key,name string)
 alter drop test (id)
 update test key name
 alter drop test (id)





 用户相关
 create user username password
 permisson user add update
 permisson user drop update

 事务（未完成）
 transaction commit
 transaction begin

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
        UserUtil.init();
        InputScanner inputScanner = InputScanner.getSingle();
        UserUtil.checkUserState();
        User user = UserUtil.getCurrentUser();
        while (inputScanner.hasNext()){
            String command = inputScanner.getInput();
            Command parserResult = CommandParser.parser(command);
            if(user.permisson(parserResult)){
                System.out.println("抱歉,无权限");
                continue;
            }
            CommandHandle handle = CommandHandleHolder.getHandle(parserResult);
            try {
                handle.handle(command);
                System.out.println("执行完毕");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            UserUtil.checkUserState();
            user = UserUtil.getCurrentUser();
        }
    }
}
