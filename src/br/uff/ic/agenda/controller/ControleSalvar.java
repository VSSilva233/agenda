package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class ControleSalvar extends KeyAdapter {

    private final JList<Contato> listaContatos;
    private final JTextField campoNome;
    private final JTextField campoTelefone;
    private final JTextField campoEnderecoComercial;
    private final JTextField campoEnderecoResidencial;
    private final JTextArea campoDetalhes;

    public ControleSalvar (JList<Contato> listaContatos, JTextField campoNome, JTextField campoTelefone, JTextArea campoDetalhes, JTextField enderecoComercial, JTextField enderecoResidencial) {
        this.listaContatos = listaContatos;
        this.campoNome = campoNome;
        this.campoTelefone = campoTelefone;
        this.campoDetalhes = campoDetalhes;
        this.campoEnderecoComercial = enderecoComercial;
        this.campoEnderecoResidencial = enderecoResidencial;
    }
    
    @Override
    public void keyReleased(KeyEvent e)  {
        Contato contatoSelecionado = listaContatos.getSelectedValue();
        if (contatoSelecionado != null) {
            contatoSelecionado.setNome(campoNome.getText());
            contatoSelecionado.setTelefone(campoTelefone.getText());
            contatoSelecionado.setDetalhes(campoDetalhes.getText());
            contatoSelecionado.setEnderecoComercial(campoEnderecoComercial.getText());
            contatoSelecionado.setEnderecoResidencial(campoEnderecoResidencial.getText());
        }
        listaContatos.repaint();        
    }    
}