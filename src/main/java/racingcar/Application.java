package racingcar;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Car{
    private final String name;
    private int position = 0;
    //차 이름
    public Car(String name){
        if (name == null || name.trim().isEmpty() || name.length() >5){
            throw new IllegalArgumentException("자동차 이름은 1자 이상 5자 이하만 가능합니다.");
        }
        this.name = name.trim();
    }
    //이동
    public void move(){
        Random random = new Random();
        if (random.nextInt(10)>=4){
            this.position++;
        }
    }
    //이름 입력
    public String getname(){
        return name;
        }
    //위치
    public int getPosition(){
        return position;
    }
    //현 상태
    public String getstatus(){
        StringBuilder status = new StringBuilder(name + " : ");
        for (int i = 0; i < position; i++) {
            status.append("-");
        }
        return status.toString();
    }

}



public class Application {
    public static void main(String[] args){
        try{
            Scanner sc = new Scanner(System.in);
            //이름 입력받기
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            String[] carnames = sc.nextLine().split(",");
            List<Car> cars = new ArrayList<>();
            for (String name : carnames){
                cars.add(new Car(name));
            }
            //회수 입력받기
            System.out.println("시도할 회수는 몇회인가요?");
            int racecount = sc.nextInt();
            if (racecount<=0){
                throw new IllegalArgumentException ("시도 횟수는 양수여야 합니다.");
            }
            //경주
            System.out.println("\n실행 결과");
            for (int i=0; i<racecount; i++){
                raceround(cars);
                printroundresult(cars);
            }

            printwinners(cars);
        }
        catch(IllegalArgumentException e){
            System.out.println("[ERROR] " + e.getMessage());
        }
    }
    private static void raceround(List<Car> cars){
            for (Car car : cars){
                car.move();
            }
    }

    private static void printroundresult(List<Car> cars){
        for (Car car : cars) {
            System.out.println(car.getstatus());
        }
        System.out.println();
    }

    private static void printwinners(List<Car> cars) {
        int maxposition = 0;
        for (Car car : cars) {
            if (car.getPosition() > maxposition) {
                maxposition = car.getPosition();
            }
        }
        List<String> winnerNames = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition() == maxposition) {
                winnerNames.add(car.getname());
            }
        }
        //우승자 출력
        System.out.println("최종 우승자 : " + String.join(", ", winnerNames));
    }
}
