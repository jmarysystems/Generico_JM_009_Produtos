/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtos_imagens;

import br.com.jmary.home.Home;
import br.com.jmary.home.imagens.Imagens_Internas;
import br.com.jmary.home.jpa.DAOGenericoJPA;
import br.com.jmary.home.jpa.JPAUtil;
import br.com.jmary.utilidades.Exportando;
import br.com.jmary.utilidades.ExtensionFileFilter;
import br.com.jmary.utilidades.JOPM;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import produtos.Produtos_02_Cadastrar_Visualizar;
import produtos_beans.ProdutosImagens;
import visualizador_imagens_beans.UsuarioImagens;

/**
 *
 * @author admin
 */
public class Visualizador_Imagens_Produtos_Banco_de_Dados extends javax.swing.JPanel {
    
    String tabela          = "PRODUTOS_IMAGENS";
    String id_outra_tabela = "ID_PRODUTOS"; // "SELECT * FROM JM." +tabela+ " WHERE " +id_outra_tabela+ " = ?"
         
    Home Home;
    int id_produto = 0;
    Produtos_02_Cadastrar_Visualizar Produtos_02_Cadastrar_Visualizar = null;
    String status_cadastro;
    
    int largura = 0;
    int altura = 0;
        
    //////////////////////////////
    //List<UsuarioImagens> UsuarioImagens_Lista     = null;
    int                  int_UsuarioImagens_Lista = 0;
    int                  qtdArquivos = 0;
    int                  id_imagem = 0;
    //////////////////////////////
    
    /**
     * Creates new form Visualizador_Interno2
     * @param Home2
     * @param id_usuario2
     * @param status_cadastro2
     * @param Produtos_02_Cadastrar_Visualizar2
     */
    public Visualizador_Imagens_Produtos_Banco_de_Dados( Home Home2, int id_usuario2, String status_cadastro2, 
            Produtos_02_Cadastrar_Visualizar Produtos_02_Cadastrar_Visualizar2 ){
        initComponents();
        
        Home = Home2;        
        id_produto = id_usuario2;
        status_cadastro = status_cadastro2;
        Produtos_02_Cadastrar_Visualizar = Produtos_02_Cadastrar_Visualizar2;
                        
        setar_Dados_Iniciais();
    }
    
    private void setar_Dados_Iniciais(){
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 ); 

            if(status_cadastro.equalsIgnoreCase("Cadastrando...")){

                
            }
            else if(status_cadastro.equalsIgnoreCase("Visualizando...")){

                if( id_produto > 0 ){
                    setarImagemInicio();
                }
                
                pesquisarImagem.setEnabled(false);
                lbExcluir.setEnabled(false);
            }
            else if(status_cadastro.equalsIgnoreCase("Alterando...")){
                
                if( id_produto > 0 ){
                    setarImagemInicio();
                }
            }
        } catch( Exception e ){  } } }.start();
    }
    
    private void setarImagemInicio(){ 
    new Thread() {   @Override public void run() { try { 
         
        List<ProdutosImagens> ProdutosImagens = null;
        try{ 
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM." +tabela+ " WHERE " +id_outra_tabela+ " = ?", ProdutosImagens.class );
            q.setParameter(1, id_produto );
            List<ProdutosImagens> lista_Banco = q.getResultList();   
            ProdutosImagens = lista_Banco;
        }catch( Exception e ){ }
        
        String rbusca = ""; 
        try{ rbusca = ProdutosImagens.get(0).getNome(); }catch( Exception e ){}
            
        if( !rbusca.equals("") ){	
            
	    BufferedImage bufImg = null;
            ImageIcon     icon   = null;
            Image         image  = null;
            try{
                
                bufImg = ImageIO.read(new ByteArrayInputStream( (byte[]) ProdutosImagens.get(int_UsuarioImagens_Lista).getImagem())); //ImageIO.read( img );
                icon   = new ImageIcon(bufImg);
                image  = icon.getImage();//ImageIO.read(f);  
            } catch (Exception ex) {} 
            
	    try{
                
                //image = icon.getImage();//ImageIO.read(f);  
                int widith = image.getWidth(icon.getImageObserver() ) + largura;
                int height = image.getHeight(icon.getImageObserver() )+ altura;
                                                                        
                lbConteudo_Online.setIcon(new ImageIcon(image.getScaledInstance(
                    widith, height, Image.SCALE_DEFAULT)));

                
                ///////////////////////////////////////////
                id_imagem = ProdutosImagens.get(int_UsuarioImagens_Lista).getId();
                qtdArquivos = ProdutosImagens.size();
                System.out.println("qtdArquivos: " + qtdArquivos);
                System.out.println("int_UsuarioImagens_Lista: " + int_UsuarioImagens_Lista);
                
                lbEsquerdo.setEnabled(true);
                lbHome.setEnabled(true);
                lbDireito.setEnabled(true);
                
                if(status_cadastro.equalsIgnoreCase("Visualizando...")){
                    
                    lbExcluir.setEnabled(false);
                }
                else{
                    
                    lbExcluir.setEnabled(true);
                }                
                ////////////////////////////////////////////
                
		//ImageIO.write(img, "PNG", new File("C:/Downloads/h.png"));
	    }catch(Exception e){ e.printStackTrace(); }          
        }
                            
    } catch( Exception e ){ } } }.start();
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
        jPanel4 = new javax.swing.JPanel();
        pesquisarImagem = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbEsquerdo = new javax.swing.JLabel();
        lbDireito = new javax.swing.JLabel();
        lbHome = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbExcluir = new javax.swing.JLabel();
        btSalvar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btExpandir = new javax.swing.JButton();
        lbConteudo_Online = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        pesquisarImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/zoom_out.png"))); // NOI18N
        pesquisarImagem.setToolTipText("PESQUISAR NOVA IMAGEM");
        pesquisarImagem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pesquisarImagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pesquisarImagemMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pesquisarImagemMouseReleased(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/FonteAum.png"))); // NOI18N
        jLabel8.setToolTipText("AUMENTAR");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        lbEsquerdo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/laranja_anterior.png"))); // NOI18N
        lbEsquerdo.setToolTipText("ANTERIOR");
        lbEsquerdo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbEsquerdo.setEnabled(false);
        lbEsquerdo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbEsquerdoMousePressed(evt);
            }
        });

        lbDireito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/laranja_proximo.png"))); // NOI18N
        lbDireito.setToolTipText("PRÓXIMO");
        lbDireito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbDireito.setEnabled(false);
        lbDireito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbDireitoMousePressed(evt);
            }
        });

        lbHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/laranja_home.png"))); // NOI18N
        lbHome.setToolTipText("HOME");
        lbHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbHome.setEnabled(false);
        lbHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbHomeMousePressed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/FonteDim.png"))); // NOI18N
        jLabel7.setToolTipText("DIMINUIR ");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });

        lbExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/lixo2.png"))); // NOI18N
        lbExcluir.setToolTipText("EXCLUIR IMAGEM");
        lbExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbExcluir.setEnabled(false);
        lbExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbExcluirMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbExcluirMouseReleased(evt);
            }
        });

        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/sql.png"))); // NOI18N
        btSalvar.setToolTipText("SALVAR NO BANCO DE DADOS");
        btSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btSalvar.setEnabled(false);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        btSalvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btSalvarKeyPressed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/FontePadrao.png"))); // NOI18N
        jLabel3.setToolTipText("TAMANHO PADRÃO");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        btExpandir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/expandir.png"))); // NOI18N
        btExpandir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btExpandir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btExpandirKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lbExcluir)
                .addGap(8, 8, 8)
                .addComponent(pesquisarImagem)
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(36, 36, 36)
                .addComponent(lbEsquerdo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDireito)
                .addGap(27, 27, 27)
                .addComponent(btExpandir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 51, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pesquisarImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDireito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExpandir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEsquerdo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel7, lbDireito, lbHome, pesquisarImagem});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lbConteudo_Online.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbConteudo_Online.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbConteudo_Online.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/anao.gif"))); // NOI18N
        lbConteudo_Online.setToolTipText("");
        lbConteudo_Online.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbConteudo_Online.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lbConteudo_OnlineKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(">>");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<<");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbConteudo_Online, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbConteudo_Online, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    Exportando Exportando;
    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        int_UsuarioImagens_Lista++;
        setarImagem_da_Lista();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        int_UsuarioImagens_Lista--;
        setarImagem_da_Lista();
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        largura -= 5;
        altura -= 5;
        setarImagem_da_Lista();
    }//GEN-LAST:event_jLabel7MousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        largura += 5;
        altura += 5;
        setarImagem_da_Lista();
    }//GEN-LAST:event_jLabel8MousePressed

    private void lbEsquerdoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbEsquerdoMousePressed
        int_UsuarioImagens_Lista--;
        if( lbEsquerdo.isEnabled() == true ){
            lbEsquerdo.setEnabled(false);
            setarImagem_da_Lista();
            lbEsquerdo.setEnabled(true);
        }
    }//GEN-LAST:event_lbEsquerdoMousePressed

    private void lbDireitoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDireitoMousePressed
        int_UsuarioImagens_Lista++;
        if( lbDireito.isEnabled() == true ){
            lbDireito.setEnabled(false);
            setarImagem_da_Lista();
            lbDireito.setEnabled(true);
        }
    }//GEN-LAST:event_lbDireitoMousePressed

    private void lbHomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbHomeMousePressed
        int_UsuarioImagens_Lista=0;
        if( lbHome.isEnabled() == true ){
            lbHome.setEnabled(false);
            setarImagem_da_Lista();
            lbHome.setEnabled(true);
        }
    }//GEN-LAST:event_lbHomeMousePressed

    private void setarImagem_da_Lista(){ 
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

                if( (int_UsuarioImagens_Lista < qtdArquivos) && (int_UsuarioImagens_Lista >= 0) ){

                    BufferedImage bufImg = null;
                    ImageIcon     icon   = null;
                    Image         image  = null;
                    try{
                        
                        List<ProdutosImagens> ProdutosImagens = null;                        
                        try{ 
                            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM." +tabela+ " WHERE " +id_outra_tabela+ " = ?", ProdutosImagens.class );
                            q.setParameter(1, id_produto );
                            List<ProdutosImagens> lista_Banco = q.getResultList();   
                            ProdutosImagens = lista_Banco;
                        }catch( Exception e ){ }
                        
                        id_imagem = ProdutosImagens.get(int_UsuarioImagens_Lista).getId();
                
                        bufImg = ImageIO.read(new ByteArrayInputStream( (byte[]) ProdutosImagens.get(int_UsuarioImagens_Lista).getImagem())); //ImageIO.read( img );
                        icon   = new ImageIcon(bufImg);
                        image  = icon.getImage();//ImageIO.read(f);  
                    } catch (Exception ex) {} 
            
	            try{
                
                        //image = icon.getImage();//ImageIO.read(f);  
                        int widith = image.getWidth(icon.getImageObserver() ) + largura;
                        int height = image.getHeight(icon.getImageObserver() )+ altura;
                                                                        
                        lbConteudo_Online.setIcon(new ImageIcon(image.getScaledInstance(
                            widith, height, Image.SCALE_DEFAULT)));
                                               
                        System.out.println("setarImagem_da_Lista 1 - qtdArquivos: " + qtdArquivos);
                        System.out.println("setarImagem_da_Lista 1 - int_UsuarioImagens_Lista: " + int_UsuarioImagens_Lista);
                        
                    }catch(Exception ex){}
                }
                else{   
                    
                    if( int_UsuarioImagens_Lista <= 0){
                        int qtd_Arquivos_da_Pasta = qtdArquivos-1;
                        int_UsuarioImagens_Lista = qtd_Arquivos_da_Pasta;
                    }
                    else{
                        int_UsuarioImagens_Lista = 0;
                    }
                    
                    BufferedImage bufImg = null;
                    ImageIcon     icon   = null;
                    Image         image  = null;
                    try{
                        
                        List<ProdutosImagens> ProdutosImagens = null;                        
                        try{ 
                            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM." +tabela+ " WHERE " +id_outra_tabela+ " = ?", ProdutosImagens.class );
                            q.setParameter(1, id_produto );
                            List<ProdutosImagens> lista_Banco = q.getResultList();   
                            ProdutosImagens = lista_Banco;
                        }catch( Exception e ){ }
                        
                        id_imagem = ProdutosImagens.get(int_UsuarioImagens_Lista).getId();
                        
                        bufImg = ImageIO.read(new ByteArrayInputStream( (byte[]) ProdutosImagens.get(int_UsuarioImagens_Lista).getImagem())); //ImageIO.read( img );
                        icon   = new ImageIcon(bufImg);
                        image  = icon.getImage();//ImageIO.read(f);  
                    } catch (Exception ex) {}  

                    try {  
                        //image = icon.getImage();//ImageIO.read(f);  
                        int widith = image.getWidth(icon.getImageObserver() ) + largura;
                        int height = image.getHeight(icon.getImageObserver() )+ altura;
                                                                        
                        lbConteudo_Online.setIcon(new ImageIcon(image.getScaledInstance(
                            widith, height, Image.SCALE_DEFAULT)));
                                                
                        System.out.println("setarImagem_da_Lista 2 - qtdArquivos: " + qtdArquivos);
                        System.out.println("setarImagem_da_Lista 2 - int_UsuarioImagens_Lista: " + int_UsuarioImagens_Lista);
                    }catch(Exception ex){}
                }
            
        } catch( InterruptedException e ){ } } }.start();
    }
    
    private void pesquisarImagemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesquisarImagemMousePressed
        if( pesquisarImagem.isEnabled() ){
            
            escolherImagem_Externa();  
        }
    }//GEN-LAST:event_pesquisarImagemMousePressed

    private void pesquisarImagemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pesquisarImagemMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisarImagemMouseReleased
    
    private void lbExcluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExcluirMousePressed
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );

            if( lbExcluir.isEnabled() ){
                try{
                    lbExcluir.setEnabled(false);
                    
                    Exportando = new Exportando("EXCLUINDO...");
                    Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                    Exportando.pbg.setMaximum( 100 );
                    Exportando.pbg.setValue( 50 );                    
                    
                     List<ProdutosImagens> ProdutosImagens_Excluir = null;
                    try{ 
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM." +tabela+ " WHERE " +id_outra_tabela+ " = ? AND ID = ?", ProdutosImagens.class );
                        q.setParameter(1, id_produto );
                        q.setParameter( 2, id_imagem );
                        
                        ProdutosImagens_Excluir = q.getResultList();   
                    }catch( Exception e ){ }
        
                    String rbusca = ""; 
                    try{ rbusca = ProdutosImagens_Excluir.get(0).getNome(); }catch( Exception e ){}
            
                    if( !rbusca.equals("") ){
            
                        DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( ProdutosImagens_Excluir.get(0), JPAUtil.em());
                        DAOGenericoJPA2.excluir();
                        
                        Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "IMAGEM EXCLUÍDA\n"
                            + "\nStatus Da Exclusão:"
                            + "\nImagem Excluída com Sucesso\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                        
                        verificar_apos_exclusao();
                        //Usuarios_Do_Sistema_02_Cadastrar_Visualizar.setarImagem_Principal();
                     }
                    
                    lbExcluir.setEnabled(true);
                    Exportando.fechar();
                } catch( Exception e ){
                    lbExcluir.setEnabled(true);
                    Exportando.fechar();
                }
            }
        } catch( Exception e ){ } } }.start(); 
    }//GEN-LAST:event_lbExcluirMousePressed

    private void verificar_apos_exclusao(){ 
    new Thread() {   @Override public void run() { try { 
             
        List<ProdutosImagens> lista_Banco = null;
        try{ 
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM." +tabela+ " WHERE " +id_outra_tabela+ " = ?", ProdutosImagens.class );
            q.setParameter(1, id_produto );
            lista_Banco = q.getResultList();   
        }catch( Exception e ){ }
        
        String rbusca = ""; 
        try{ rbusca = lista_Banco.get(0).getNome(); }catch( Exception e ){}
            
        if( !rbusca.equals("") ){	 
	          
            try{
                
                ///////////////////////////////////////////
                id_imagem = lista_Banco.get(0).getId();
                qtdArquivos = lista_Banco.size();
                
                int_UsuarioImagens_Lista = 0;
                setarImagem_da_Lista();
                
                lbEsquerdo.setEnabled(true);
                lbHome.setEnabled(true);
                lbDireito.setEnabled(true);
                lbExcluir.setEnabled(true);
                ////////////////////////////////////////////
                
		//ImageIO.write(img, "PNG", new File("C:/Downloads/h.png"));
	    }catch(Exception e){ e.printStackTrace(); }  
        }
        else{
            
            lbConteudo_Online.setIcon(null);            
            ///////////////////////////////////////////
            id_imagem = 0;
            qtdArquivos = 0;
                
            lbEsquerdo.setEnabled(false);
            lbHome.setEnabled(false);
            lbDireito.setEnabled(false);
            lbExcluir.setEnabled(false);
            ////////////////////////////////////////////
        }
                            
    } catch( Exception e ){ } } }.start();
    }
    
    private void lbExcluirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExcluirMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbExcluirMouseReleased

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if( btSalvar.isEnabled() ){
            
            new Thread() {   @Override public void run() { try { 
                
                if(status_cadastro.equalsIgnoreCase("Cadastrando...")){
                    
                    try{ Produtos_02_Cadastrar_Visualizar.lista_de_Endereco_imagemExterna_.add( imagemExterna_Endereco.getPath() ); } catch( Exception e ){} 
                    try{ 
                        
                        File img = new File( imagemExterna_Endereco.getPath() );
                        Produtos_02_Cadastrar_Visualizar.setarImagemExterna_Endereco_for( img ); 
                    } catch( Exception e ){}
                    
                    Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "SELECIONAMENTO DA IMAGEM\n"
                            + "\nStatus Do Selecionamento:"
                            + "\nImagem Selecionada com Sucesso\n"
                            + "\nPara concluir clique em salvar o Usuário\n"    
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }
                else{
                    
                    salvar();
                }                
            } catch( Exception e ){ } } }.start();
        }   
    }//GEN-LAST:event_btSalvarActionPerformed

    private void lbConteudo_OnlineKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbConteudo_OnlineKeyPressed
        
    }//GEN-LAST:event_lbConteudo_OnlineKeyPressed

    private void btSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btSalvarKeyPressed
        try{
            
            if (evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_KP_RIGHT) {
             
                int_UsuarioImagens_Lista++;
                setarImagem_da_Lista(); 
                
                new Thread() {   @Override public void run() { try { 
                    Thread.sleep( 100 );
                    btSalvar.requestFocus();
                    Thread.sleep( 1000 );
                    btSalvar.requestFocus();
                } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();
                
            }
            else if (evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_KP_LEFT) {
            
                int_UsuarioImagens_Lista--;
                setarImagem_da_Lista();
                new Thread() {   @Override public void run() { try { 
                    Thread.sleep( 100 );
                    btSalvar.requestFocus();
                    Thread.sleep( 1000 );
                    btSalvar.requestFocus();
                } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();
            }
        } catch( Exception e ){}
    }//GEN-LAST:event_btSalvarKeyPressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        largura = 0;
        altura = 0;
        setarImagem_da_Lista();
    }//GEN-LAST:event_jLabel3MousePressed

    private void btExpandirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btExpandirKeyPressed
        try{
            
            if (evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_KP_RIGHT) {
             
                int_UsuarioImagens_Lista++;
                setarImagem_da_Lista(); 
                
                new Thread() {   @Override public void run() { try { 
                    Thread.sleep( 100 );
                    btExpandir.requestFocus();
                    Thread.sleep( 1000 );
                    btExpandir.requestFocus();
                } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();
                
            }
            else if (evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_KP_LEFT) {
            
                int_UsuarioImagens_Lista--;
                setarImagem_da_Lista();
                new Thread() {   @Override public void run() { try { 
                    Thread.sleep( 100 );
                    btExpandir.requestFocus();
                    Thread.sleep( 1000 );
                    btExpandir.requestFocus();
                } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();
            }
        } catch( Exception e ){}
    }//GEN-LAST:event_btExpandirKeyPressed

    private void escolherImagem_Externa() {                                      
        new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
            JFileChooser JFileChooserJMPasta = new JFileChooser();
            
            ///////////////////////////////////////////////////////////////////////////
            FileFilter filter = new ExtensionFileFilter ( "jpg", "png" ) ;
            JFileChooserJMPasta.setFileFilter ( filter ) ;
            JFileChooserJMPasta.setFileSelectionMode(JFileChooser.FILES_ONLY);
            ///////////////////////////////////////////////////////////////////////////
            
            int status = JFileChooserJMPasta.showSaveDialog(null);
                
            if ( status == javax.swing.JFileChooser.CANCEL_OPTION ){ 

                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "SELECIONAMENTO DA IMAGEM\n"
                            + "\nStatus Do Selecionamento:"
                            + "\nImagem não Selecionada\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                        
            }else if ( status == javax.swing.JFileChooser.APPROVE_OPTION ){
                
                File arquivo = JFileChooserJMPasta.getSelectedFile();
                
                Exportando = new Exportando("ABRINDO...");
                Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( 100 );
                Exportando.pbg.setValue( 50 );
 
                setarImagemExterna_Endereco(arquivo);

                Exportando.fechar();
            }
            /*
            JFileChooserJM JFileChooserJM = new JFileChooserJM( "  *.png/.jpg    -   jmarysystems.blogspot.com.br", new String [] { "png" , "jpg" } );
            String strdevolvida = JFileChooserJM.getString( 3 );
            System.out.println(strdevolvida);
            
            File f  = new File( strdevolvida );
            if ( f.exists() ) { 
                
                Exportando = new Exportando("ABRINDO...");
                Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
                Exportando.pbg.setMaximum( 100 );
                Exportando.pbg.setValue( 50 );
 
                setarImagemExterna_Endereco(f);

                Exportando.fechar();
            }
            else{
                
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                        JOPM JOptionPaneMod = new JOPM( 1, "SELECIONAMENTO DA IMAGEM\n"
                            + "\nStatus Do Selecionamento:"
                            + "\nImagem não Selecionada\n"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            */
        } catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();
    } 
    
    File imagemExterna_Endereco = null;
    private void setarImagemExterna_Endereco(File img){ 

        /*new Thread() {   @Override public void run() {*/ try { 
            
            BufferedImage bufImg = null;
            ImageIcon     icon   = null;
            Image         image  = null;
            try{
                        bufImg = ImageIO.read( img );
                        icon   = new ImageIcon(bufImg);
                        image  = icon.getImage();//ImageIO.read(f);  
                    } catch (IOException ex) {}  

                    try {  
                        image = icon.getImage();//ImageIO.read(f);  
                        int widith = image.getWidth(icon.getImageObserver() ) + largura;
                        int height = image.getHeight(icon.getImageObserver() )+ altura;
                                                                        
                        lbConteudo_Online.setIcon(new ImageIcon(image.getScaledInstance(
                            widith, height, Image.SCALE_DEFAULT)));
                       
                        imagemExterna_Endereco = img;
                        btSalvar.setEnabled(true);
                    }catch(Exception ex){}
                
                    String nome = img.getName();
                    String nomeList[] = nome.split(Pattern.quote("."));
                
        } catch( Exception e ){ } //} }.start();
    }
    
    private void salvar(){ 

        try { 
            
            BufferedImage bufImg = null;
            ImageIcon     icon   = null;
            Image         image  = null;
            try{
                
                bufImg = ImageIO.read( imagemExterna_Endereco );
                icon   = new ImageIcon(bufImg);
                image  = icon.getImage();//ImageIO.read(f);  
            } catch (IOException ex) {}  

            
                String nome = imagemExterna_Endereco.getName();
                String nomeList[] = nome.split(Pattern.quote("."));
                
                ProdutosImagens ProdutosImagens = new ProdutosImagens();
                
                ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
                String extencao = imagemExterna_Endereco.getPath().substring( imagemExterna_Endereco.getPath().lastIndexOf('.') + 1 ); 
                
                ImageIO.write((BufferedImage)image, extencao, bytesImg);//seta a imagem para bytesImg
                bytesImg.flush();//limpa a variável
                byte[] byteArray = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[] 
                bytesImg.close();//fecha a conversão
                
                ProdutosImagens.setImagem(byteArray);
                
                try{ ProdutosImagens.setIdProdutos(id_produto ); }catch( Exception e ){}
                try{ ProdutosImagens.setNome( nomeList[0] ); }catch( Exception e ){}
                
                try{
                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( ProdutosImagens, JPAUtil.em());
                    ProdutosImagens ProdutosImagens_Cadastrado = (ProdutosImagens) DAOGenericoJPA2.gravanovoOUatualizar();
                
                    /////////////////////////////////////////////////
                    try{ 
                        Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM." +tabela+ " WHERE " +id_outra_tabela+ " = ?", ProdutosImagens.class );
                        q.setParameter(1, id_produto );
                        List<ProdutosImagens> lista_Banco = q.getResultList();   
                        ///////////////////////////////////
                        id_imagem = ProdutosImagens_Cadastrado.getId();
                        qtdArquivos = lista_Banco.size();
                        System.out.println("atual - qtdArquivos: " + qtdArquivos);
                        System.out.println("atual - int_UsuarioImagens_Lista: " + int_UsuarioImagens_Lista);
                
                        lbEsquerdo.setEnabled(true);
                        lbHome.setEnabled(true);
                        lbDireito.setEnabled(true);
                        lbExcluir.setEnabled(true);
                        
                        int_UsuarioImagens_Lista=0;
                        setarImagem_da_Lista();
                        btSalvar.setEnabled(false);
                        //////////////////////////////////
                    }catch( Exception e ){ }
                    /////////////////////////////////////////////////
                    
                    //try{ Produtos_02_Cadastrar_Visualizar.setarImagem_Principal(); }catch( Exception e ){}
                    
                    Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "CADASTRO\n"
                        + "\nSTATUS DO CADASTRO:"
                        + "\nCADASTRADO COM SUCESSO!\n"
                        + "\nOK Para Prosseguir"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
                }catch( Exception e ){
                    
                    JOPM JOptionPaneMod = new JOPM( 2, "ERRO AO CADASTRAR, 1"
                        + "\nNÃO CADASTRADO"
                        + "\nNenhum dado CADASTRADO."
                        + "\n", "CADASTRAR" );
                }
        } catch( Exception e ){ 
        
            JOPM JOptionPaneMod = new JOPM( 2, "ERRO AO CADASTRAR, 2"
                + "\nNÃO CADASTRADO"
                + "\nNenhum dado CADASTRADO."
                + "\n", "CADASTRAR" );
        } 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExpandir;
    private javax.swing.JButton btSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbConteudo_Online;
    private javax.swing.JLabel lbDireito;
    private javax.swing.JLabel lbEsquerdo;
    private javax.swing.JLabel lbExcluir;
    private javax.swing.JLabel lbHome;
    private javax.swing.JLabel pesquisarImagem;
    // End of variables declaration//GEN-END:variables
        
}