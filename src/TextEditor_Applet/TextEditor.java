package TextEditor_Applet;
import javax.swing.undo.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.*;
import java.io.File;
import java.util.Hashtable;

public class TextEditor extends  TextArea  implements  StateEditable{
    private final static String KEY_STATE="UndoableTextAreaKey";
    private boolean textUnchanged=false;
    private UndoManager undoManager;
    private StateEdit stateEdit;
    public TextEditor(){
        super();
        initUndoable();
    }
    public TextEditor(String string){
        super(string);
        initUndoable();
    }
    public TextEditor(int row,int column){
        super(row,column);
        initUndoable();
    }
    public TextEditor(String string,int row,int column){
        super(string,row,column);
        initUndoable();
    }
    public TextEditor(String string,int row,int column,int scrollBars){
        super(string,row,column,scrollBars);
        initUndoable();
    }
    public boolean Undo(){
        try{
            undoManager.undo();
            return true;
        }catch (CannotUndoException e){
            System.out.println("Can't Undo Manager");
            return false;
        }
    }
    public boolean Redo(){
        try{
            undoManager.redo();
            return true;
        }catch (CannotRedoException e){
            System.out.println("Can't Redo Manager");
            return false;
        }
    }
    public void storeState(Hashtable state){
        state.put(KEY_STATE.getText());
    }
    public void restoreState(Hashtable state){
        Object data=state.get(KEY_STATE);
        if(data!=null){
            setText((String)data);
        }
    }
    public void takeSnapShot(){
        if(textUnchanged){
            stateEdit.end();
            undoManager.addEdit(stateEdit);
            textUnchanged=false;
            stateEdit=new StateEdit(this);
        }
    }
    private void initUndoable(){
        undoManager=new UndoManager();
        stateEdit=new StateEdit(this);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event){
                if(event.isActionKey()){
                    takeSnapShot();
                }
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                takeSnapShot();
            }
        });
        addTextListener(new TextListener() {
            @Override
            public void textValueChanged(TextEvent e) {
                textUnchanged=true;
                takeSnapShot();
            }
        });
    }

}
public class TextEditorB extends Frame{
    boolean b=true;
    Frame fm;
    FileDialog fd;
    Font f;
    int style=Font.PLAIN;
    int fsize=12;
    UndoableTextArea txt;
    String fileName,st,fn="untitled",dn;
    Clipboard clip=getToolkit().getSystemClipboard();
    TextEditorB(){
        f=new Font("Courier",style,fsize);
        setLayout(new GridLayout(1,1));
        txt=new UndoableTextArea(80,25);
        txt.setFont(f);
        add(txt);
        MenuBar menuBar=new MenuBar();
        Menu menu=new Menu("FontType");
        MenuItem one,two,three,four,five,six;
        one=new MenuItem("TimesRoman");
        two =new MenuItem("halvetica");
        three=new MenuItem("Courier");
        four=new MenuItem("Arial");
        five=new MenuItem("Arial Black");
        six=new MenuItem("century");
        menu.add(one);menu.add(two);menu.add(three);menu.add(four);menu.add(five);menu.add(six);
        one.addActionListener(new Type());two.addActionListener(new Type());three.addActionListener(new Type());
        four.addActionListener(new Type());five.addActionListener(new Type());six.addActionListener(new Type());

        Menu fontmenu=new Menu("Font");
        MenuItem boldmenu=new MenuItem("Bold");
        MenuItem plain=new MenuItem("Plain");
        MenuItem italian=new MenuItem("Italic");
        fontmenu.add(boldmenu);fontmenu.add(plain);fontmenu.add(italian);
        boldmenu.addActionListener(new FM());
        plain.addActionListener(new FM());
        italian.addActionListener(new FM());

        Menu size=new Menu("Size");
        MenuItem s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
        s1=new MenuItem("10");s2=new MenuItem("12");s3=new MenuItem("14");
        s4=new MenuItem("16");s5=new MenuItem("18");s6=new MenuItem("20");
        s7=new MenuItem("22");s8=new MenuItem("24");s9=new MenuItem("26");
        s10=new MenuItem("28");
        size.add(s1);size.add(s2);size.add(s3);size.add(s4);size.add(s5);size.add(s6);size.add(s7);size.add(s8);size.add(s9);size.add(s10);
        s1.addActionListener(new Size());s2.addActionListener(new Size());s3.addActionListener(new Size());
        s4.addActionListener(new Size());s5.addActionListener(new Size());s6.addActionListener(new Size());
        s7.addActionListener(new Size());s8.addActionListener(new Size());s9.addActionListener(new Size());
        s10.addActionListener(new Size());
        fontmenu.add(size);

        Menu file=new Menu("File");
        MenuItem n=new MenuItem("New",new MenuShortcut(KeyEvent.VK_N));
        MenuItem o=new MenuItem("Open",new MenuShortcut(KeyEvent.VK_O));
        MenuItem s=new MenuItem("Save",new MenuShortcut(KeyEvent.VK_S));
        MenuItem e=new MenuItem("Exit", new MenuShortcut(KeyEvent.VK_E));
        file.add(n);n.addActionListener(new New());
        file.add(o);o.addActionListener(new Open());
        file.add(s);s.addActionListener(new Save());
        file.add(e);e.addActionListener(new Exit());
        menuBar.add(file);
        addWindowListener(new Win());

        Menu edit=new Menu("Edit");
        MenuItem cut=new MenuItem("Cut",new MenuShortcut(KeyEvent.VK_X));
        MenuItem copy=new MenuItem("Copy" ,new MenuShortcut(KeyEvent.VK_C));
        MenuItem paste=new MenuItem("Paste",new MenuShortcut(KeyEvent.VK_V));
        edit.add(cut);cut.addActionListener(new Cut());
        edit.add(copy);copy.addActionListener(new Copy());
        edit.add(paste);paste.addActionListener(new Paste());

        Menu color=new Menu("Color");
        MenuItem back=new MenuItem("Background");
        MenuItem forground=new MenuItem("Foreground");
        back.addActionListener(new BC());
        forground.addActionListener(new BC());
        color.add(back);
        color.add(forground);

        Menu undo=new Menu("Undo&Redo");
        MenuItem un=new MenuItem("Undo");
        MenuItem re=new MenuItem("Redo");
        un.addActionListener(new WW());re.addActionListener(new WW());
        undo.add(un);undo.add(re);
        menuBar.add(edit);menuBar.add(fontmenu);menuBar.add(menu);menuBar.add(color);menuBar.add(undo);

        setMenuBar(menuBar);
        mylistener mylist=new mylistener();
        addWindowListener(mylist);


    }
    class WW implements ActionListener{
        public  void actionPerformed(ActionEvent e){
            String se=e.getActionCommand();
            if(se.equals("undo"))
                txt.undo();
            if(se.equals("Redo"))
                txt.redo();
        }
    }
    class mylistener extends windowAdapter{
        public void windowClosing(WindowEvent we){
            if(!b){
                System.exit(0);
            }
        }
    }
    class New implements ActionListener{
        public void actionPerformed(ActionEvent e){
            txt.setText(" ");
            setTitle(fileName);
            fn="untitled";
        }
    }
    class Open implements ActionListener{
        public void actionPerformed(ActionEvent e){
            FileDialog fd=new FileDialog(TextEditorB.this,"Select");
            fd.show();
            if((fn=fd.getFile())!=null){
                fileName=fd.getDirectory()+fd.getFile();
                dn=fd.getDirectory();
                setTitle(fileName);
                readFile();
            }
            txt.requestFocus();
        }
    }
    class Save implements ActionListener{
        public void actionPerformed(ActionEvent e){
            FileDialog fd=new FileDialog(TextEditorB.this,"SaveFile",FileDialog());
            fd.setFile(fn);
            fd.setDirectory(dn);
            fd.show();
            if((fn=fd.getFile())!=null){
                fileName=fd.getDirectory()+fd.getFile();
                //dn=fd.getDirectory();
                setTitle(fileName);
                writeFile();
                txt.requestFocus();
            }

        }
    }


}