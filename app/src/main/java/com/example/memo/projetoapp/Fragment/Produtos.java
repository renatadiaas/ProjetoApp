package com.example.memo.projetoapp.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.memo.projetoapp.Dados;
import com.example.memo.projetoapp.Activity.Descricao;
import com.example.memo.projetoapp.R;
import com.example.memo.projetoapp.RespostaServidor;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Produtos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Produtos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Produtos extends Fragment implements AdapterView.OnItemClickListener,RespostaServidor {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayAdapter<String> listViewAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String[] items;
    private OnFragmentInteractionListener mListener;
    private ListView listview;

    public Produtos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Produtos.
     */
    // TODO: Rename and change types and number of parameters
    public static Produtos newInstance(String param1, String param2) {
        Produtos fragment = new Produtos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_produtos, container, false);

        Dados.getInstance().setRespostaServidorProdutos(this);


        listview =(ListView) v.findViewById(R.id.listview);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Dados.getInstance().getProdutos());

        listview.setAdapter(listViewAdapter);
        listview.setOnItemClickListener(this);


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent j = new Intent(getActivity(), Descricao.class);
        j.putExtra("posicao",i);
        startActivity(j);



    }

    @Override
    public void respostaAtualizado(final ArrayList<String> produtos) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("NovoTeste", ""+produtos.size());
                listViewAdapter =  new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, produtos);
                listview.setAdapter(listViewAdapter);
                listViewAdapter.notifyDataSetChanged();
            }
        });

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
