public class Grade {

    String examName;
    float weight;
    float points;

    public Grade(String examName, float weight, float points) {
        this.examName = examName;
        this.weight = weight;
        this.points = points;
    }
    
    // Getter and setter for 'examName'
    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    // Getter and setter for 'weight'
    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    // Getter and setter for 'points'
    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public String toString(){

        return this.examName + " (Weight: " + this.weight +
        ") " + this.points;
    }
}
