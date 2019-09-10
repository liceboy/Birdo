package birdo;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class application
  extends JFrame
{
  private static final long serialVersionUID = -6596113243078458151L;
  
  public application()
  {
    initUI();
  }
  
  private void initUI()
  {
    add(new main());
    setSize(500, 525);
    setResizable(false);
    setTitle("birdo");
    setDefaultCloseOperation(3);
    setLocationRelativeTo(null);
  }
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        application ex = new application();
        ex.setVisible(true);
      }
    });
  }
}
