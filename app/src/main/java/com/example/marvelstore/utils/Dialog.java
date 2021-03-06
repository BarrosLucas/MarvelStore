package com.example.marvelstore.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.example.marvelstore.R;
import com.example.marvelstore.view.CartActivity;
import com.example.marvelstore.view.HomeActivity;

import java.util.ArrayList;

public class Dialog {

    /*Dialog para quando o usuário finaliza a compra. Ao finalizar, o carrinho é "zerado", o cupom
    * que o usuário havia deixado salvo apaga que a tela volta para a HOME*/
    public static void showAlertDialogToFinishPurchase(View view, Context context, CartActivity cartActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        builder.setTitle("Completed Purchase");
        builder.setMessage("Congratulations! You just purchased one of our products!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HomeActivity.comics = new ArrayList<>();
                HomeActivity.coupon = null;
                cartActivity.finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /*Para tratamento de erros em geral o Alert é acionado para alertar o usuário
    * - Falhas na conexão
    * - Cupom inválido*/
    public static void showAlertDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
