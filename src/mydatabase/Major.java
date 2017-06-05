package mydatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

public class Major {
    private Connection conn = Database.conn;
    public int id;
    public String name;
    public String academy_name;
    /*返回结果栈类 参数可选*/
    public Stack<Major> search(String name){
        Stack<Major> stack = new Stack<Major>();
        try{
            String command;
            command="SELECT * FROM major";
            if(name.length() > 0){
                String tc=" WHERE major_name='"+name+"'";
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
                Major t=new Major();
                t.id=rs.getInt("major_id");
                t.name=rs.getString("major_name");
                try{
                    PreparedStatement tpStmt=null;
                    tpStmt=conn.prepareStatement("SELECT * FROM academy WHERE academy_id="+rs.getInt("academy_id")+";");
                    conn.setAutoCommit(false);
                    conn.commit();
                    ResultSet trs = tpStmt.executeQuery();
                    trs.next();
                    t.academy_name=trs.getString("academy_name");
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
    public boolean insert(String name,String academy_name){
        boolean result = false;
        try{
            Academy a=new Academy();
            Stack<Academy> stack=a.search(academy_name, "");

            String command;
            command="insert into major(major_name,academy_id) values (?,?)";
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
            command="DELETE FROM major WHERE major_name='"+name+"';";
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
            command="UPDATE major SET major_name=? WHERE major_name=?";
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
