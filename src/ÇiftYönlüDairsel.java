
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ÇiftYönlüDairsel extends JFrame implements ActionListener {

	Timer tm = new Timer(5, this);
	int x, y;

	public ÇiftYönlüDairsel() {
		setTitle("Çift Yönlü Dairesel Baðlý Liste");
		setSize(1000, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(255, 204, 204));

		JLabel lblSayi = new JLabel("Sayý gir:");
		lblSayi.setBounds(10, 11, 70, 40);
		getContentPane().add(lblSayi);

		final JTextField txtSayi = new JTextField();
		txtSayi.setBounds(60, 11, 70, 40);
		txtSayi.setColumns(5);
		getContentPane().add(txtSayi);

		JButton btnBasaEkle = new JButton("Basa Ekle");
		btnBasaEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sayi = Integer.parseInt(txtSayi.getText());
				a.basaekle(sayi);
				txtSayi.setText("");
			}
		});
		btnBasaEkle.setBounds(100, 11, 180, 40);
		getContentPane().add(btnBasaEkle);

		JButton btnSonaEkle = new JButton("Ekle");
		btnSonaEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sayi = Integer.parseInt(txtSayi.getText());
				a.ekle(sayi);
				txtSayi.setText("");
			}
		});
		btnSonaEkle.setBounds(280, 11, 180, 40);
		getContentPane().add(btnSonaEkle);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sayi = Integer.parseInt(txtSayi.getText());
				a.sil(sayi);
				txtSayi.setText("");
			}
		});
		btnSil.setBounds(460, 11, 180, 40);
		getContentPane().add(btnSil);
	
	JButton btnAra = new JButton("Ara");
	btnAra.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			int sayi = Integer.parseInt(txtSayi.getText());
			a.Ara(sayi);
			txtSayi.setText("");
		}
	});
	btnAra.setBounds(640, 11, 180, 40);
	getContentPane().add(btnAra);
}
	

	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 90, 1080, 90);
		tm.start();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	class liste {
		public dugum headNode, tailNode, aktif;

		public liste() {
			// headNode = null;
			// tailNode = null;
		}

		public void basaekle(int sayi) {
			dugum yeniNode = new dugum(sayi);

			if (headNode == null) {
				x = 380;
				y = 200;
				headNode = yeniNode;
				tailNode = yeniNode;
				yeniNode.lbl.setLocation(x, y);
				
				yeniNode.lbl.setVisible(true);
				yeniNode.lbl.setText("" + sayi);
				getContentPane().add(yeniNode.lbl);
				headNode = yeniNode;
				tailNode = yeniNode;
				headNode.onceki = null;
				headNode.sonraki = null;
				tailNode.sonraki = null;
				tailNode.onceki = null;
				x=x-100;
				y=y+100;
			
			} else {
				x=headNode.lbl.getX();
				headNode.onceki = yeniNode;
				yeniNode.sonraki = headNode;
				headNode = yeniNode;
				headNode.onceki = tailNode;
				yeniNode.lbl.setLocation(x - 100, y);
				yeniNode.lbl.setVisible(true);
				yeniNode.lbl.setText("" + sayi);
				getContentPane().add(yeniNode.lbl);
				
				
			}
			tailNode.sonraki = headNode;
			
		}

		public void ekle(int sayi) {
			dugum aktif = headNode;
			dugum yeniNode = new dugum(sayi);
			yeniNode.sonraki = aktif.sonraki;
			aktif.sonraki.onceki = yeniNode;
			aktif.sonraki = yeniNode;
			yeniNode.onceki = aktif;
			yeniNode.lbl.setLocation(x+100, y);//sebep sor
			x=x+100;
			y=y+100;
			yeniNode.lbl.setVisible(true);
			yeniNode.lbl.setText("" + sayi);
			getContentPane().add(yeniNode.lbl);
			
		}

		
		public void sil(int sayi) {
			if (headNode != null) {
				dugum aktif = headNode;

				while ((aktif.sonraki.sayi != sayi) && (aktif.sonraki != null))
					aktif = aktif.sonraki;

				if (aktif.sonraki.sayi == sayi) {
					getContentPane().remove(aktif.sonraki.sonraki.onceki.lbl);
					aktif.sonraki.sonraki.onceki = aktif;
					aktif.sonraki = aktif.sonraki.sonraki;
				}
			}
		}
			public void Ara(int aranan)
			{
				dugum donusDegeri = null;
				if (headNode != null)
				{
					dugum aktif = headNode;
					while ((aktif != null))
					{
						if (aktif.sayi == aranan)
						{
							donusDegeri = aktif;
							break;
						} else
						{
							aktif = aktif.sonraki;
						}
					}
				}
		}

		class dugum {
			int sayi;
			dugum sonraki;
			dugum onceki;
			JLabel lbl;

			public dugum(int sayi) {
				this.sayi = sayi;
				lbl = new JLabel(new ImageIcon("rect1.png"));
				lbl.setText("" + sayi);
				lbl.setHorizontalTextPosition(JLabel.CENTER);
				lbl.setVerticalTextPosition(JLabel.CENTER);
				lbl.setFont(new Font("Arial", Font.ITALIC, 20));
				lbl.setBounds(x, y, 100, 44);
				lbl.setVisible(true);
			}
		}

	}

	liste a = new liste();

	public static void main(String[] args) {
		ÇiftYönlüDairsel a = new ÇiftYönlüDairsel();
	}
}
