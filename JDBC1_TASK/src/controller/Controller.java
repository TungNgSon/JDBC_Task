/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Moment
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.studentFrame;
import objs.*;
public class Controller {
    private studentFrame frame;
    private DefaultTableModel tableModel;
    private ArrayList<SinhVien> sv;
    private String previousId;
    public Controller()
    {
        frame =new studentFrame();
        tableModel=new DefaultTableModel();
        sv=new ArrayList<SinhVien>();
        addTableColumns();
        frame.getSvTable().setModel(tableModel);
        frame.getSvTable().setAutoCreateRowSorter(true);
       
    }
    public void display()
    {
        frame.setVisible(true);
        addEvent();
    }
    private void addTableColumns()
    {
        tableModel.addColumn("MaSV");
        tableModel.addColumn("Ho Ten");
        tableModel.addColumn("Lop");
        tableModel.addColumn("GPA");
    }
    private void renderTable()
    {
        sv=MyJDBC.getSV();
        tableModel.setRowCount(0);
        for(SinhVien x:sv)
        {
            tableModel.addRow(x.toRowTable());
        }
        
    }
    private void addEvent()
    {
        addClickEvent();
        addDisplayButtonEvent();
        addAddButtonEvent();
        addResetButtonEvent();
        addUpdateButtonEvent();
        addDeleteButtonEvent();
    }
    private void addDisplayButtonEvent()
    {
        frame.getDisplayButton().addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
              renderTable();
            }
        });
    }
    private void addAddButtonEvent()
    {
        frame.getAddButton().addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
            String id=frame.getIdField().getText();
            String name=frame.getNameField().getText();
            String classId=frame.getClassIdField().getText();
            String gpaString=frame.getGpaField().getText();
            SinhVien sv=new SinhVien(id,name,classId,Double.parseDouble(gpaString));
            if(checkInput(id,name,classId,gpaString))
            {
                if(MyJDBC.checkSV(id))
                {
                    MyJDBC.add(sv);
                    renderTable();
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Nguoi dung da ton tai");
                }
            }
            }
        });
    }
    private void addDeleteButtonEvent()
    {
        frame.getDeleteButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=frame.getSvTable().getSelectedRow();
                if(row!=-1)
                {
                    String id=(String)frame.getSvTable().getValueAt(row, 0);
                    MyJDBC.delete(id);
                    renderTable();
                }
                setTextField("","","","");
            }
        });
    }
    private void addResetButtonEvent()
    {
        frame.getResetButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyJDBC.deleteAll();
                renderTable();
            }
            
        });
    }
    private void setTextField(String id,String name,String classId,String gpaString)
    {
        frame.getIdField().setText(id);
        frame.getNameField().setText(name);
        frame.getClassIdField().setText(classId);
        frame.getGpaField().setText(gpaString);
    }
    private void addClickEvent()
    {
        frame.getSvTable().addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int row=frame.getSvTable().getSelectedRow();
                if(row!=-1)
                {
                    String id=(String)frame.getSvTable().getValueAt(row, 0);
                    String name=(String)frame.getSvTable().getValueAt(row, 1);
                    String classId=(String)frame.getSvTable().getValueAt(row, 2);
                    Double gpa=(Double)frame.getSvTable().getValueAt(row, 3);
                    String gpaString=Double.toString(gpa);
                    previousId=id;
                    setTextField(id,name,classId,gpaString);
                }
                
                
                
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
    private void addUpdateButtonEvent()
    {
        frame.getSetButton().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=frame.getIdField().getText();
                String name=frame.getNameField().getText();
                String classId=frame.getClassIdField().getText();
                String gpaString=frame.getGpaField().getText();
                SinhVien sv=new SinhVien(id,name,classId,Double.parseDouble(gpaString));
                if(checkInput(id,name,classId,gpaString))
                {
                    if(!previousId.equals(id)&&!MyJDBC.checkSV(id))
                    {
                        JOptionPane.showMessageDialog(frame, "Nguoi dung da ton tai");
                    }
                    else
                    {
                        MyJDBC.update(previousId, sv);
                        renderTable();
                        
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Input khong hop le");
                }
                setTextField("","","","");
                
            }
        });
    }
    private boolean checkInput(String id,String name,String classId,String gpaString)
    {
        
        if(id.isEmpty()||name.isEmpty()||classId.isEmpty()||gpaString.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "Input khong hop le");
            return false;
        }
        return true;
        
    }
}
