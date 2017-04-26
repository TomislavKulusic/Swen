import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nikola on 26.04.2017..
 */
public class ImageView  implements ActionListener {


    private ArrayList<File> files;
    private ArrayList<CustomImage> customImages;
    public static JFrame frame;

    public ImageView(){

        frame = new JFrame();

        frame.setLayout(new GridLayout(0, 5));
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File workingDirectory = new File(System.getProperty("user.dir"));
        jfc.setCurrentDirectory(workingDirectory);
        int returnVal = jfc.showOpenDialog(frame);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            Loader loader = new Loader(jfc.getSelectedFile().getAbsolutePath());
            files = loader.getFiles();
            customImages = loader.getImages(files);
            populateGrid(customImages);
        }

        frame.setVisible(true);
    }

    public void populateGrid(ArrayList<CustomImage> customImages){


        for(int i = 0; i < customImages.size(); i++){
            CustomImage customImage = customImages.get(i);
            JPanel imagePanel = new JPanel();
            JButton imageIcon = new JButton(customImage.getImageReScaled(100, 100));


            imageIcon.addActionListener(
                    new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            try {
                                new ImageEdit(customImage);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });

            imagePanel.add(imageIcon);
            frame.add(imagePanel);
        }

    }





    public void actionPerformed(ActionEvent e) {


    }


    public static void main(String[]args){

        new ImageView();

    }
}
