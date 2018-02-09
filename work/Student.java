public class Student {
    private int Id; // идентификационный номер
    private String fio; //Fio - фамилия и инициалы
    private Group group; //ссылка на группу (объект Group)
    private int marks[]; //массив оценок
    private int Num; // количество оценок
    public static int N=10; // макисмально возможное количество оценок

    public Student(int id, String fio){
        this.Id=id;
        this.fio=fio;
        group=null;
        Num=0;
        marks=new int[N];
        for (int i=0;i<N;i++) marks[i]=-1;
    }
    public String getFio(){
        return fio;
    }
    public int getId(){
        return Id;
    }
    public void addGroup(Group group){
        this.group=group;
    }
    public Group getGroup(){return group;}
    public void addMark(int mark) {
        if (Num <= N) {
            marks[Num] = mark;
            Num++;
        }
    }
    public double getAverageMark(){
        double mark=0;
        if (Num!=0){
            for (int i=0;i<Num;i++)
                mark+=marks[i];
            mark/=Num;
        }
        return mark;
    }
    public void printMark(){
        for (int i=0;i<Num;i++)
        System.out.printf("%d ",marks[i]);
        System.out.printf("   средняя оценка = %.2f",getAverageMark());
    }


}
