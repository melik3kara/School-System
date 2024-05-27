import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class School {

    Student[] students;
    int numOfStudents;

    public School(){

        students = new Student[10];
    }

    public void addStudent(String schoolID, String name, String surname, int age){
            
        for (Student student : students) {
            if(student != null && student.getSchoolID().equals(schoolID)){
                throw new IllegalArgumentException("Duplicate ID: " + schoolID);
            }
        }
        Student student = new Student(name, surname, schoolID, age);
        numOfStudents ++;
        for (int i = 0; i < students.length; i++) {
            if(students[i]==null){
                students[i]= student;
                break;
            }
        }
        if(numOfStudents == students.length/2 ){
            Student[] newArr = new Student[students.length * 2];
            for (int i = 0; i < students.length; i++) {
                newArr[i] = students[i];
            }
            this.students = newArr;
        }
        sortByID();
    }

    public void sortByID(){
        int length = 0;
        for (int i = 0; i < students.length; i++) {
            if(students[i]!=null){length++;}
        }

        int n = length;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if(this.students[j].getSchoolID().compareTo(this.students[j+1].getSchoolID()) > 0){
                    Student temp = this.students[j];
                    students[j] = students[j+1];
                    students[j+1] = temp;
                }
            }
        }
    }

    public Student getStudent(String schoolID){

        int left = 0;
        int right = numOfStudents - 1;
        //try {
        
            while(left <= right){
                int mid = left + (right - left)/2;

                if(students[mid].getSchoolID().equals(schoolID)){
                    return students[mid];
                }

                if(students[mid].getSchoolID().compareTo(schoolID) < 0){
                    left = mid + 1;
                }

                else{
                    right = mid - 1;
                }
            }
        
        
        throw new IllegalArgumentException("No such student with the id: " +schoolID + "!" );
    }   

    public Student[] getStudentsByNameOrder(){

        Student[] studentArr = new Student[numOfStudents];
        for (int i = 0; i < studentArr.length; i++) {
            studentArr[i] = students[i];
        }
        int low = 0;
        int high = studentArr.length - 1;

        orderHelper(low, high, studentArr);

        return studentArr;
    }

    public void orderHelper(int low, int high, Student[] studentArr){

        if(low < high){

            int pi = partition(studentArr, low, high);
            orderHelper(low, pi - 1, studentArr);
            orderHelper(pi + 1, high, studentArr);
        }
    }

    public int partition(Student[] studentArr, int low, int high) {
        Student pivot = studentArr[high]; 
        int i = (low - 1);  

        for (int j = low; j < high; j++) {
            
            if (studentArr[j].getName().compareTo(pivot.getName()) < 0) {

                i++;
                Student temp = studentArr[i];
                studentArr[i] = studentArr[j];
                studentArr[j] = temp;
            }

            else if(studentArr[j].getName().compareTo(pivot.getName())==0){

                if(studentArr[j].getSurname().compareTo(pivot.getSurname()) < 0){

                    i++;
                    Student temp = studentArr[i];
                    studentArr[i] = studentArr[j];
                    studentArr[j] = temp;
                }
            }
        }

        Student temp = studentArr[i + 1];
        studentArr[i+1] = studentArr[high];
        studentArr[high] = temp;

        return i + 1;
    }

    public void printStudentsByNameOrder(){

        Student[] studentsArr = getStudentsByNameOrder();

        for (Student student : studentsArr) {
            
            System.out.println(student.toString());
        }
    }

    public void printStudent(){

        for (Student student : students) {
            if(student!=null)
            System.out.println(student.toString());
        }
    }

    public float getGradeAverage(Student s){

        float totalWeight = 0;
        float totalPoint = 0;

        for (int i = 0; i < s.grades.length; i++) {

            totalWeight += s.grades[i].getWeight();
            totalPoint += (s.grades[i].getPoints() * s.grades[i].getWeight());
        }

        if(totalWeight != 0){
            float num = (totalPoint) / (totalWeight);
            return num;
        }
        return 0;
    }

    public void printStudentGradeAverages(){

        Student[] studentArr = sortByGradeAv();

        for (Student student : studentArr) {
            System.out.println(student.toString() + " - Average: " + getGradeAverage(student));
        }
    }

    public Student[] sortByGradeAv(){

        Student[] studentArr = new Student[numOfStudents];
        for (int i = 0; i < studentArr.length; i++) {
            studentArr[i] = students[i];
        }

        int n = studentArr.length;

        for (int i = 0; i < n - 1 ; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if(getGradeAverage(studentArr[j]) < getGradeAverage(studentArr[j + 1])){

                    Student temp = studentArr[j];
                    studentArr[j] = studentArr[j + 1];
                    studentArr[j + 1] = temp;
                }
                if(getGradeAverage(studentArr[j]) == getGradeAverage(studentArr[j+1])){

                    if(studentArr[j].getSchoolID().compareTo(studentArr[j+1].getSchoolID()) < 0){

                        Student temp = studentArr[j];
                        studentArr[j] = studentArr[j + 1];
                        studentArr[j + 1] = temp;
                    }
                }
            }
        }
        return studentArr;
    }

    public void printGradesOf(String schoolID){

        Student s = getStudent(schoolID);

        System.out.println("Student: " + s.toString());
        System.out.println("Grades: ");

        for (Grade grade : s.getGrades()) {

            System.out.println(grade.toString());
        }
    }

    public void processTextFile(String filename){

        try (BufferedReader bufread = new BufferedReader(new FileReader(filename))){
            
            String line;

            while((line = bufread.readLine()) != null){

                String[] parts = line.split(",|:");
                
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                if(parts.length > 0){

                    String choice = parts[0];

                    switch (choice){
                        case "Student":

                        if(parts.length > 3){
                            try{
                                String[] name = parts[1].split(" ");
                                String studentName = name[0];
                                String studentSurname = name[1];
                                int studentAge = Integer.parseInt(parts[2]);
                                String studentID = parts[3];

                                addStudent(studentID, studentName, studentSurname, studentAge);
                            }
                            catch(Exception e){
                                System.err.println(e.getMessage());
                                System.out.println();
                            }
                        }
                        break;

                        case "Grade":
                        if(parts.length > 4){
                            
                            String studentID = parts[1];
                            String examName = parts[2];
                            float weight = Float.parseFloat(parts[3]);
                            float grade = Float.parseFloat(parts[4]);

                            boolean control =  false;

                            for (Student s : students) {
                                if(s!=null){

                                
                                    if(s.getSchoolID().equals(studentID)){

                                        control = true;
                                        try {

                                            s.setGrades(examName, weight, grade);

                                        } 
                                        catch (Exception e) {

                                         System.err.println(e.getMessage());
                                         System.out.println();
                                        }
                                        break;
                                    }
                                }
                            }
                            if(!control){
                                System.err.println("Student not found with ID: " + studentID);
                                System.out.println();
                            }
                        }
                        break;

                        case "GradesOf":
                        if(parts.length > 1){

                            try{
                                String studentID = parts[1];
                                if(getStudent(studentID)!=null){

                                    Student s = getStudent(studentID);
                                    System.out.println("Grades of " + s.toString());
                                    Grade[] grades = s.getGrades();
                                    for (Grade grade : grades) {
                                        System.out.println(grade.toString());
                                    }
                                }
                            }
                            catch(Exception e){
                                System.err.println(e.getMessage());
                            }
                        }
                        System.out.println();
                        break;

                        case "PrintByNameOrder":
                        if(parts.length >= 1){
                            printStudentsByNameOrder();
                            System.out.println();
                        }
                        break;

                        case "PrintByGradeAverages":
                        if(parts.length >= 1){
                            printStudentGradeAverages();
                            System.out.println();
                        }
                        break;

                        case "PrintStudents":
                        if(parts.length >= 1){
                            printStudent();
                            System.out.println();
                        }
                        break;
                    }
                }

            }
        }
            
        catch (IOException e) {
            System.err.println("error cannot read this file: " + e.getMessage());
        }
    }
}