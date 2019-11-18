package br.uff.ic.agenda.controller;

import br.uff.ic.agenda.model.Contato;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControleCarregar implements ListSelectionListener {

    private final JList<Contato> listaContatos;
    private final JTextField campoNome;
    private final JTextField campoTelefone;
    private final JTextArea campoDetalhes;
    private final JTextField campoEnderecoComercial;
    private final JTextField campoEnderecoResidencial;

    public ControleCarregar(JList<Contato> listaContatos, JTextField campoNome, JTextField campoTelefone, JTextArea campoDetalhes, JTextField campoEnderecoComercial, JTextField campoEnderecoResidencial) {
        this.listaContatos = listaContatos;
        this.campoNome = campoNome;
        this.campoTelefone = campoTelefone;
        this.campoDetalhes = campoDetalhes;
        this.campoEnderecoComercial = campoEnderecoComercial;
        this.campoEnderecoResidencial = campoEnderecoResidencial;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Contato contatoSelecionado = listaContatos.getSelectedValue();
        
        if (contatoSelecionado != null) {
            campoNome.setText(contatoSelecionado.getNome());
            campoTelefone.setText(contatoSelecionado.getTelefone());
            campoDetalhes.setText(contatoSelecionado.getDetalhes());
            campoEnderecoComercial.setText(contatoSelecionado.getEnderecoComerial());
            campoEnderecoResidencial.setText(contatoSelecionado.getEnderecoResidencial());
        } else {
            campoNome.setText("");
            campoTelefone.setText("");
            campoDetalhes.setText("");
            campoEnderecoComercial.setText("");
            campoEnderecoResidencial.setText("");
        }

        campoNome.setEnabled(contatoSelecionado != null);
        campoTelefone.setEnabled(contatoSelecionado != null);
        campoDetalhes.setEnabled(contatoSelecionado != null);
        campoEnderecoComercial.setEnabled(contatoSelecionado != null);
        campoEnderecoResidencial.setEnabled(contatoSelecionado != null);
        listaContatos.repaint();        
    }
}