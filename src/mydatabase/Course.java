package mydatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

public class Course {
    private Connection conn = Database.conn;
    public int id;
    public String name;
    public int time;
    public double score;
    public String teacher_name;
    /*返回结果栈类 参数可选*/
    public Stack<Course> search(String name){
        Stack<Course> stack = new Stack<Course>();
        try{
            String command;
            command="SELECT * FROM course";
            if(name.length() > 0){
                String tc=" WHERE course_name='"+name+"'";
                command=command+tc;
            }
            command=command+";";
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            conn.commit();
            ResultSet rs = pStmt.executeQuery();

            while(rs.next()){
                Course t=new Course();
                t.id=rs.getInt("course_id");
                t.name=rs.getString("course_name");
                t.time=rs.getInt("course_time");
                t.score=rs.getDouble("course_score");
                try{
                    String tcommand="SELECT * FROM teacher WHERE teacher_id='"+rs.getString("teacher_id")+"';";
                    tcommand=new String(tcommand.getBytes(),"UTF-8");
                    PreparedStatement tpStmt=null;
                    tpStmt=conn.prepareStatement(tcommand);
                    conn.setAutoCommit(false);
                    conn.commit();
                    ResultSet trs = tpStmt.executeQuery();
                    trs.next();
                    t.teacher_name=trs.getString("teacher_name");
                    trs.close();
                    tpStmt.close();
                }catch(Exception e){
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }
                stack.push(t);
            }

            rs.close();
            pStmt.close();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return stack;
    }

    /*返回操作在数据库中是否执行了  两个参数都为必选*/
    public boolean insert(String name,int time,double score,String teacher_name){
        boolean result = false;
        try{
            Teacher a=new Teacher();
            Stack<Teacher> stack=a.search("",teacher_name);

            String command;
            command="insert into course(course_name,course_time,course_score,teacher_id) values (?,?,?,?)";
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setString(1,name);
            pStmt.setInt(2,time);
            pStmt.setDouble(3,score);
            pStmt.setString(4,stack.pop().id);
            result = pStmt.executeUpdate() > 0;
            pStmt.close();
            conn.commit();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return result;
    }

    /*返回操作在数据库中是否执行了 参数必选*/
    public boolean delete(String name){
        boolean result = false;
        try{
            String command;
            command="DELETE FROM course WHERE course_name='"+name+"';";
            command=new String(command.getBytes(),"UTF-8");
            Statement stmt;
            stmt = conn.createStatement();
            result = stmt.executeUpdate(command) > 0;
            stmt.close();
            conn.commit();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return result;
    }

    /*返回操作在数据库中是否执行了  参数第一个必选，其他随意，time和score若没有值请置为0*/
    public boolean update(String name,String toname,int time,double score){
        boolean result = false;
        try{
            boolean pre=false;
            String command;
            command="UPDATE course SET ";
            if(toname.length() > 0){
                if(pre){
                    command=command+",";
                }
                String tc="course_name='"+toname+"'";
                command=command+tc;
                pre=true;
            }
            if(time > 0){
                if(pre){
                    command=command+",";
                }
                String tc="course_time="+time;
                command=command+tc;
                pre=true;
            }
            if(score > 0){
                if(pre){
                    command=command+",";
                }
                String tc="course_score="+score;
                command=command+tc;
                pre=true;
            }
            String tc=" WHERE course_name=?";
            command=command+tc;
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setString(1,name);
            result = pStmt.executeUpdate() > 0;
            pStmt.close();
            conn.commit();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return result;
    }

}
