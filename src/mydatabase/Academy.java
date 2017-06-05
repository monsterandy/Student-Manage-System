package mydatabase;

import java.sql.*;
import java.util.Stack;

public class Academy {
    private Connection conn = Database.conn;
    public int id;
    public String name;
    public String leader;
    /*返回结果栈类    两个参数皆为可选，空值请传入""参数*/
    public Stack<Academy> search(String name,String leader){
        Stack<Academy> stack = new Stack<Academy>();
        try{
            boolean flag=false;
            String command;
            command="SELECT * FROM academy";
            if(name.length() > 0){
                if(flag==false){
                    command=command+" WHERE ";
                }
                String tc="academy_name='"+name+"'";
                command=command+tc;
                flag=true;
            }
            if(leader.length() > 0){
                if(flag){
                    command=command+" AND ";
                }
                else{
                    command=command+" WHERE ";
                }
                String tc="academy_leader='"+leader+"'";
                command=command+tc;
                flag=true;
            }
            command=command+";";
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            conn.commit();
            ResultSet rs = pStmt.executeQuery();

            while(rs.next()){
                Academy t=new Academy();
                t.id=rs.getInt("academy_id");
                t.name=rs.getString("academy_name");
                t.leader=rs.getString("academy_leader");
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
    public boolean insert(String name,String leader){
        boolean result = false;
        try{
            String command;
            command="insert into academy(academy_name,academy_leader) values (?,?)";
            command=new String(command.getBytes(),"UTF-8");
            PreparedStatement pStmt=null;
            pStmt=conn.prepareStatement(command);
            conn.setAutoCommit(false);
            pStmt.setString(1,name);
            pStmt.setString(2,leader);
            result = pStmt.executeUpdate() > 0;
            pStmt.close();
            conn.commit();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
        return result;
    }

    /*返回操作在数据库中是否执行了 两个参数皆为可选，但必须须选择一个，空值请传入""参数*/
    public boolean delete(String name,String leader){
        boolean result = false;
        try{
            boolean flag=false;
            String command;
            command="DELETE FROM academy";
            if(name.length() > 0){
                if(flag==false){
                    command=command+" WHERE ";
                }
                String tc="academy_name='"+name+"'";
                command=command+tc;
                flag=true;
            }
            if(leader.length() > 0){
                if(flag){
                    command=command+" AND ";
                }
                else{
                    command=command+" WHERE ";
                }
                String tc="academy_leader='"+leader+"'";
                command=command+tc;
                flag=true;
            }
            command=command+";";
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

    /*返回操作在数据库中是否执行了  第一个参数必选，其他随意但是至少要选一个*/
    public boolean update(String name,String toname,String leader){
        boolean result = false;
        try{
            boolean pre=false;
            String command;
            command="UPDATE academy SET ";
            if(toname.length() > 0){
                if(pre){
                    command=command+",";
                }
                String tc="academy_name='"+toname+"'";
                command=command+tc;
                pre=true;
            }
            if(leader.length() > 0){
                if(pre){
                    command=command+",";
                }
                String tc="academy_leader='"+leader+"'";
                command=command+tc;
                pre=true;
            }
            String tc=" WHERE academy_name=?";
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
