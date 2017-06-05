package mydatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

public class Class {
    private Connection conn = Database.conn;
    public int id;
    public String name;
    public String major_name;
    /*返回结果栈类 参数可选*/
    public Stack<Class> search(String name){
        Stack<Class> stack = new Stack<Class>();
        try{
            String command;
            command="SELECT * FROM class";
            if(name.length() > 0){
                String tc=" WHERE class_name='"+name+"'";
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
                Class t=new Class();
                t.id=rs.getInt("class_id");
                t.name=rs.getString("class_name");
                try{
                    PreparedStatement tpStmt=null;
                    tpStmt=conn.prepareStatement("SELECT * FROM major WHERE major_id="+rs.getInt("major_id")+";");
                    conn.setAutoCommit(false);
                    conn.commit();
                    ResultSet trs = tpStmt.executeQuery();
                    trs.next();
                    t.major_name=trs.getString("major_name");
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
    public boolean insert(String name,String major_name){
        boolean result = false;
        try{
            Major a=new Major();
            Stack<Major> stack=a.search(major_name);

            String command;
            command="insert into class(class_name,major_id) values (?,?)";
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setString(1,name);
            pStmt.setInt(2,stack.pop().id);
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
            command="DELETE FROM class WHERE class_name='"+name+"';";
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

    /*返回操作在数据库中是否执行了  两个参数都为必选，将名字修改为另一个名字*/
    public boolean update(String name,String toname){
        boolean result = false;
        try{
            String command;
            command="UPDATE class SET class_name=? WHERE class_name=?";
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setString(1,toname);
            pStmt.setString(2,name);
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
