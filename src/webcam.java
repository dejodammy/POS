/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.SecureRandom;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author Damilola
 */
public class webcam extends javax.swing.JFrame {

    static {
        File file = new File("C:\\Users\\Damilola\\OneDrive\\Documentos\\opencv_java249.dll");
        System.load(file.getAbsolutePath());
    }

    private static File getImage;
    private static final SecureRandom RAND = new SecureRandom();
    public static String Filename = null;
    private DaemonThread myThread = null;
    private VideoCapture websource = null;
    private final Mat frame = new Mat(1000, 1000, 1);
    private final MatOfByte mem = new MatOfByte();

    public webcam() {
        initComponents();
        websource = new VideoCapture(0);
        myThread = new DaemonThread(jLabel1);
        Thread t = new Thread(myThread);
        t.setDaemon(true);
        myThread.runnable = true;
        t.start();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowAdapter evt) {
                stopCam();
            }
        });
    }

    private void stopCam() {
        if (myThread != null) {
            if (myThread.runnable == true) {
                myThread.runnable = false;
                websource.release();
            }
        }
    }

    private void CaptureImage(JLabel image) {
        try {
            stopCam();
            if (getImage != null) {
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(getImage.getAbsolutePath()).getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
                image.setIcon(imageIcon);
            }
        } catch (Exception e) {
            // Log the exception details for debugging
            e.printStackTrace();

            // Display a more informative error message
            JOptionPane.showMessageDialog(this, "Error displaying captured image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        public DaemonThread(JLabel capture) {
            jLabel1 = capture;
        }

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    if (websource.grab()) {
                        try {
                            websource.retrieve(frame);
                            Highgui.imencode(".bmp", frame, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
                            BufferedImage buff = (BufferedImage) im;
                            Graphics g = jLabel1.getGraphics();
                            if (g.drawImage(buff, 1, 1, jLabel1.getWidth(), jLabel1.getHeight(), null)) {
                                if (runnable == false) {
                                    this.wait();
                                }
                            }
                        } catch (Exception e) {
                            // Handle exceptions
                        }
                    }
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to capture the image");

        if (option == 0) {
            try {
                File file = new File("Capture");
                boolean flag = true;
                if (!file.isDirectory()) {
                    flag = file.mkdir();
                }

                if (!flag) {
                    throw new Exception("Folder does not exist");
                }

                int imageNo = 1 + RAND.nextInt(999);
                Filename = file.getAbsolutePath() + "\\" + "Webcam" + imageNo + ".jpg";
                Highgui.imwrite(Filename, frame);
                getImage = file;
                CaptureImage(jLabel1);
                JOptionPane.showConfirmDialog(rootPane, Filename + " Captured");

                ImageIcon imageIcon = new ImageIcon(new ImageIcon(Filename).getImage().getScaledInstance(userRegistration.lbl_picture.getWidth(), userRegistration.lbl_picture.getHeight(), Image.SCALE_DEFAULT));
                userRegistration.lbl_picture.setText("");
                userRegistration.lbl_picture.setIcon(imageIcon);

                File imageFile = new File(Filename);
                FileInputStream fis = new FileInputStream(imageFile);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
                byte[] capturedImageBytes = baos.toByteArray();

                userRegistration.photo = capturedImageBytes;

                dispose();
                setVisible(false);
                new userRegistration().setVisible(true);

                ImageIcon capturedImageIcon = new ImageIcon(new ImageIcon(Filename).getImage().getScaledInstance(userRegistration.lbl_picture.getWidth(), userRegistration.lbl_picture.getHeight(), Image.SCALE_DEFAULT));
                userRegistration.lbl_picture.setIcon(capturedImageIcon);
            } catch (Exception e) {
                stopCam();
                setVisible(false);
                new userRegistration().setVisible(true);
                JOptionPane.showMessageDialog(rootPane, "Warning");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(webcam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(webcam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(webcam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(webcam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new webcam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
