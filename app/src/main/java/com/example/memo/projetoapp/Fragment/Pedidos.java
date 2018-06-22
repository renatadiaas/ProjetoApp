package com.example.memo.projetoapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.memo.projetoapp.Dados;
import com.example.memo.projetoapp.Activity.DescricaoPedido;
import com.example.memo.projetoapp.R;
import com.example.memo.projetoapp.RespostaServidor;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Pedidos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Pedidos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pedidos extends Fragment implements RespostaServidor, AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayAdapter<String> listViewAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String[] infoPedidos;
    private ListView listview;

    private OnFragmentInteractionListener mListener;

    public Pedidos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pedidos.
     */
    // TODO: Rename and change types and number of parameters
    public static Pedidos newInstance(String param1, String param2) {
        Pedidos fragment = new Pedidos();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pedidos, container, false);

        Dados.getInstance().setRespostaServidorPedidos(this);

        Dados.getInstance().inicializaPedidos();
        listview =(ListView) v.findViewById(R.id.listviewPedidos);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Dados.getInstance().getPedidos());
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
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void respostaAtualizado(final ArrayList<String> produtos) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listViewAdapter =  new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, produtos);
                listview.setAdapter(listViewAdapter);
                listViewAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent j = new Intent(getActivity(), DescricaoPedido.class);
        j.putExtra("pedido",i);
        startActivity(j);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
