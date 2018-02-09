public class Group {
    String Title; // название группы
    Student students[]; //  массив из ссылок на студентов
    int Num; // количество студентов в группе
    Student Head; // ссылка на старосту (из членов группы)
    public static int maxNumbStud=30;// максимально возможное число студентов в группе

    public Group(String title){
        Title=title;
        students=new Student[maxNumbStud];
        Num=0;
        Head=null;
    }
    public void addStudent(Student s){
        if (Num<=maxNumbStud){
            students[Num]= s;
            Num++;
            s.addGroup(this);
        }
        chooseHead();
    }
    public String getTitle(){
        return Title;
    }
    public void chooseHead(){
        double max=0;
        Student theBestStudent=null;
        for (int i=0;i<Num;i++)
            if (students[i].getAverageMark()>max) {
            max=students[i].getAverageMark();
            theBestStudent=students[i];
            }
        Head=theBestStudent;
    }

    public Student getStud(int id){
        for (int i=0;i<Num;i++){
            if (students[i].getId()==id) return students[i];
        }
        return null;
    }
    public Student getStud(String fio){
        for (int i=0;i<Num;i++){
            if (students[i].getFio().equals(fio)) return students[i];
        }
        return null;
    }
    public double getAverageMark(){
        double mark=0;
        if (Num!=0) {
            for (int i = 0; i < Num; i++) {
                mark += students[i].getAverageMark();
            }
            mark /=Num;
        }
        return mark;
    }
    public int getStudPosition(Student s){
        for (int i=0;i<Num;i++){
            if (students[i]==s) return i;
        }
        return -1;
    }

    public void deleteStud(Student s){
        int i=getStudPosition(s);
        if (i >= 0){
            students[i]=students[Num-1];
            students[Num-1]=null;
            Num--;
            s.addGroup(null);
            if (Head==s) chooseHead();
        }
    }
    public void printStudents(){
        for (int i=0;i<Num;i++) {
            System.out.printf("%-2d) %-5d  %-32s  ", i+1,   students[i].getId(), students[i].getFio());
            students[i].printMark();
            if (students[i]==Head) System.out.println(" староста");
            else System.out.println();
        }
    }



}
