import java.io.File;

public class Demo {
    public static void main(String[] args){
        Dekanat a=new Dekanat();
        File stud=new File("Students.txt");
        File group=new File("Groups.txt");


        System.out.println("1) ЗАГРУЖАЕМ ДАННЫЕ О СТУДЕНТАХ И ГРУППАХ ИЗ ФАЙЛОВ, ФОРМИРУЕМ ГРУППЫ, ПРОСТАВЛЯЕМ ОЦЕНКИ, ВЫБИРАЕМ СТАРОСТ");
        a.addStudents(stud);
        a.addGroups(group);
        a.addRandomMarks();
        a.randomDistribution();
        a.chooseHeads();
        a.printData();

        System.out.println();
        System.out.println("2) ПЕРЕВОДИМ Чертова Андрея Борисовича В ГРУППУ ПРИНЖ (если он там был,тамже и останется)");
        System.out.println();
        a.studTransfer("Чертов Андрей Борисович","ПРИНЖ");
        a.printData();

        System.out.println();
        System.out.println("3) УДАЛЯЕМ ПЛОХИХ СТУДЕНТОВ, ЧЬЯ СРЕДНЯЯ ОЦЕНКА < 3.5");
        System.out.println();
        a.deleteBadStuds();
        a.printData();


    }
}
