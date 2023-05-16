package fedorero;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Main extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;

    private JMenuItem trenie;
    // Поле, по которому прыгают мячи
    private Field field = new Field();
    public Main() {
        super("Программирование и синхронизация потоков");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
// Установить начальное состояние окна развѐрнутым на весь экран

        setExtendedState(MAXIMIZED_BOTH);
// Создать меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                field.addBall();
                if (!pauseMenuItem.isEnabled() &&
                        !resumeMenuItem.isEnabled()) {
// Ни один из пунктов меню не являются
// доступными - сделать доступным "Паузу"
                    pauseMenuItem.setEnabled(true);
                }
            }
        };

        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);
        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Приостановить движение"){
            public void actionPerformed(ActionEvent event) {
                field.pause();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);
        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                field.resume();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };
        ///////////////////////////////////////////////
     JMenu controlMagnetism = new JMenu("Прилипание");
        double x=0;

        Action enableMagnet =new AbstractAction("ВКЛ") {
            @Override
            public void actionPerformed(ActionEvent event){

// Запросить пользователя ввести искомую строку
                BouncingBall ball=new BouncingBall(true);
            }
        };
        Action disableMAgnet =new AbstractAction("ВЫКЛ") {
            @Override
            public void actionPerformed(ActionEvent event){
// Запросить пользователя ввести искомую строку
                BouncingBall ball=new BouncingBall(false);
            }
        };
        menuBar.add(controlMagnetism);
        controlMagnetism.add(enableMagnet);
        controlMagnetism.add(disableMAgnet);
////////////////////////////////////////////////////////////////////


       // double x=0;
        JMenu controltime = new JMenu("время");
        Action tren =new AbstractAction("-") {
            @Override
            public void actionPerformed(ActionEvent event){
// Запросить пользователя ввести искомую строку
                BouncingBall ball=new BouncingBall(3);
        }
        };
        Action trenplus =new AbstractAction("+") {
            @Override
            public void actionPerformed(ActionEvent event){
// Запросить пользователя ввести искомую строку
                BouncingBall ball=new BouncingBall(-3);
            }
        };
        menuBar.add(controltime);
        controltime.add(tren);
        controltime.add(trenplus);

        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);
// Добавить в центр граничной компоновки поле Field
        getContentPane().add(field, BorderLayout.CENTER);
    }
    // Главный метод приложения
    public static void main(String[] args) {
// Создать и сделать видимым главное окно приложения
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
