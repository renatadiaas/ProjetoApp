package com.example.memo.projetoapp.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.memo.projetoapp.Activity.Cadastro;
import com.example.memo.projetoapp.CarrinhoProdutos;
import com.example.memo.projetoapp.Dados;
import com.example.memo.projetoapp.Model.InfoPedido;
import com.example.memo.projetoapp.Model.ItemCarrinho;
import com.example.memo.projetoapp.R;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Carrinho.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Carrinho#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Carrinho extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listview, listview2;
    private String nome, qtd;
    private Button env;
    private CarrinhoProdutos carrinhoProdutos = CarrinhoProdutos.getInstance();

    private OnFragmentInteractionListener mListener;

    public Carrinho() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Carrinho.
     */
    // TODO: Rename and change types and number of parameters
    public static Carrinho newInstance(String param1, String param2) {
        Carrinho fragment = new Carrinho();
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

        View v=inflater.inflate(R.layout.fragment_carrinho, container, false);

        listview = v.findViewById(R.id.produto);
        env = (Button)v.findViewById(R.id.enviar);
        env.setOnClickListener(this);



        ArrayList<String> produtos = carrinhoProdutos.getNomeProdutos();
        if(listview == null){
            Log.i("CarrinhoProdutos", "ListView nula");

        }

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, produtos);
        listview.setAdapter(listViewAdapter);


                // Log.i("nome", quantidade );
        //Intent n = getActivity().getIntent();

        //String nome = (String) n.getExtras().get("nome");
        //int qtd = (int) n.getExtras().get("quantidade");







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
    public void onClick(View view) {


            Dados.getInstance().readData(new Dados.FirebaseCallback() {
                InfoPedido infoPedido = new InfoPedido();
                @Override
                public void OnCallback(ArrayList<String> informacoes) {

                    for(int i=0; i<carrinhoProdutos.getNomeProdutos().size();i++) {
                        Date hora = new Date();
                        hora.getTime();
                        String dStr = hora.toString();
                        String split [] = carrinhoProdutos.getNomeProdutos().get(i).split(" - ");
                        infoPedido.setCliente(informacoes.get(0));
                        infoPedido.setEnd(informacoes.get(1));
                        infoPedido.setNum(Integer.parseInt(informacoes.get(2)));
                        infoPedido.setProduto(split[1]);
                        infoPedido.setQtd(Integer.parseInt(split[0]));
                        Dados.getInstance().insertInfoPedidos(infoPedido, dStr);
                        CarrinhoProdutos.getInstance().tirarCarrinho();
                        informacoes.clear();
                        Log.i("olamundo2","chegueiaqui");
                    }
                }
            });

        Toast.makeText(getActivity(),"Pedido realizado",Toast.LENGTH_SHORT).show();


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
