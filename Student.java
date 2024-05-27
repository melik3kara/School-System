public class Student {

    String name;
    String surname;
    String schoolID;
    int age;
    Grade grades[];

    public Student(String name, String surname, String schoolID, int age){
        
        this.name = name;
        this.surname = surname;
        this.schoolID = schoolID;
        this.age = age;
        grades = new Grade[0];
    }
    
    // Getter and setter for 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter for 'surname'
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Getter and setter for 'schoolID'
    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    // Getter and setter for 'age'
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Getter and setter for 'grades'
    public Grade[] getGrades() {
        return grades;
    }

    public void setGrades(String name, float weight, float points) {
        if(name.length()<= 3){
            throw new IllegalArgumentException(
                "Must be longer than 3 characters!");
        }

        boolean found = false;
        for (Grade grade : grades) {
            if(grade != null && grade.getExamName().equals(name)){

                grade.setWeight(weight);
                grade.setPoints(points);
                found = true;
                break;
            }
        }
        /*Grade grade = new Grade(name, weight, points);
        for (Grade grade_arr : grades) {
            if(grade_arr.getExamName()==grade.getExamName()){
                grade_arr = grade;
                return;
            }
        }*/

        if(!found){

            Grade newArr[] = new Grade[grades.length + 1];
            for (int i = 0; i < this.grades.length; i++) {
                newArr[i] = grades[i];
            }
            newArr[newArr.length - 1] = new Grade(name, weight, points);
            this.grades = newArr;
        }
    }

    public String toString(){
        return this.schoolID + ", " + this.name + ", " 
        + this.surname + ", " + this.age;
    }
}