import java.io.*;


public class Dekanat {
    public Student Students[];
    private Group Groups[];
    private int NumStuds;
    private int NumGroups;
    private static int maxStud = 100;
    private static int maxGroup = 10;
    private File st;
    private File gr;


    public Dekanat() {
        Students = new Student[maxStud];
        Groups = new Group[maxGroup];
        NumStuds = 0;
        NumGroups = 0;
        st = null;
        gr = null;
    }

    public void addStudents(File file) {
        st = file;
        String line;
        int i = 0;
        int id;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"windows-1251"));
            while ((line = reader.readLine()) != null) {
                id = (int) (1000 * Math.random());
                Students[i] = new Student(id, line);
                i++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
        NumStuds = i;
    }
    public void addGroups(File file) {
        gr = file;
        String line;
        int i = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"windows-1251"));
            while ((line = reader.readLine()) != null) {
                Groups[i] = new Group(line);
                i++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
        NumGroups = i;
    }
    public void addRandomMarks() {
        int mark;
        for (int i = 0; i < NumStuds; i++) {
            for (int ii = 0; ii < Student.N; ii++) {
                mark = (int) (4 * Math.random() + 2);
                Students[i].addMark(mark);
            }
        }
    }
    public void statistics() {

    }
    public void studTransfer(int id, String title) { // по id студента и названию группы
        Student s = null;
        int g = -1;
        for (int i = 0; i < NumGroups; i++) {
            s = Groups[i].getStud(id);
            if (s != null) {
                Groups[i].deleteStud(s);
                break;
            }
        }
        for (int i = 0; i < NumGroups; i++) {
            if (Groups[i].getTitle().equals(title)) {
                g = i;
                break;
            }
        }
        if ((g >= 0) && (s != null)) Groups[g].addStudent(s);
    }
    public void studTransfer(String fio, String title) { // по фио и названию группы
        Student s = null;
        int g = -1;
        for (int i = 0; i < NumGroups; i++) {
            s = Groups[i].getStud(fio);
            if (s != null) {
                if (Groups[i].getTitle().equals(title)) return;
                Groups[i].deleteStud(s);
                break;
            }
        }
        for (int i = 0; i < NumGroups; i++) {
            if (Groups[i].getTitle().equals(title)) {
                g = i;
                break;
            }
        }
        if ((g >= 0) && (s != null)) Groups[g].addStudent(s);
    }
    public void studTransfer(Student s,Group g){ // по ссылкам на студента и группу
        if ((s!=null)&&(g!=null)) {
            s.getGroup().deleteStud(s);
            g.addStudent(s);
        }
    }
    public void deleteBadStuds() {
        Student s;
        int del=0; // счетчик удаленных учеников
        for (int i=0;i<NumStuds;i++) {
            s=Students[i];
            if (s.getAverageMark() < 3.5) {
                s.getGroup().deleteStud(s);
                del++;
                Students[i]=Students[NumStuds-1];
            }
        }
        NumStuds-=del;
        refreashData();
    }

    public void refreashData() {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(gr), "windows-1251"));
            for (int i=0;i<NumGroups;i++) out.write(Groups[i].getTitle()+"\r\n");
            out.close();
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(st), "windows-1251"));
            for (int i=0;i<NumStuds;i++) out.write(Students[i].getFio()+"\r\n");
            out.close();
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }
    public void chooseHeads(){
        for (int i=0;i<NumGroups;i++) {
            Groups[i].chooseHead();
        }
    }
    public void printData(){
        for (int i=0;i<NumGroups;i++){
            System.out.printf("Группа %s, средняя оценка группы : %f\n",Groups[i].getTitle(),Groups[i].getAverageMark());
            Groups[i].printStudents();
        }
    }
    public void randomDistribution(){
        for (int i=0;i<NumStuds;i++)
        {
            int rand= (int)(Math.random() * NumGroups);
            Groups[rand].addStudent(Students[i]);
        }
    }
}
