package org.itmo.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class WordAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        DocsHelper docsHelper = new DocsHelper();
        String selectedText = editor.getSelectionModel().getSelectedText();
        if (selectedText != null) {
            try {
                docsHelper.send(selectedText);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Messages.showMessageDialog("Selection is empty, could you please select something?", "Word Action", Messages.getInformationIcon());
        }
    }
    @Override
    public boolean isDumbAware() {
        return false;
    }
}