public class Hero {
    public String name; 
    protected float hp; 
 
    private static void battleWin(){
        System.out.println("battle win");
    }
    
    //�з���ˮ��
    static class EnemyCrystal{
    	int hp=5000;
    	
    	//���ˮ����Ѫ��Ϊ0��������ʤ��
    	public void checkIfVictory(){
    		if(hp==0){
    			Hero.battleWin();
    			
    			//��̬�ڲ��಻��ֱ�ӷ����ⲿ��Ķ�������
    			System.out.println(" win this game");
    		}
    	}
    }
    
    public static void main(String[] args) {
    	//ʵ������̬�ڲ���
    	EnemyCrystal crystal = new EnemyCrystal();
    	crystal.checkIfVictory();
	}
 
}
