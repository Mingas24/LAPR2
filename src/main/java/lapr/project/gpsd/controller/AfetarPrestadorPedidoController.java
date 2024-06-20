//package lapr.project.gpsd.controller;
//
//import lapr.project.gpsd.model.*;
//
//import java.util.List;
//
//public class AfetarPrestadorPedidoController {
//    private Company comp;
//    private String email;
//
//    /**
//     * Construtor que obtem a empresa com todos os registos a partir da aplicacao gpsd
//     */
//    public AfetarPrestadorPedidoController(String email) {
//        this.email=email;
//        this.comp = ApplicationGPSD.getInstance().getCompany();
//    }
//
//    /**
//     *
//     * @return pedidos registados
//     */
//    public List<PedidoPrestacaoServico> getPedidos(){
//        return this.comp.getRegistoPedidoPrestacaoServicos().getListaPedidos();
//    }
//
//    /**
//     * Retorna o melhor prestador para o pedido indicado
//     * @param ped
//     * @return Prestador
//     */
//    public PrestadorServicos getMelhorPrestador(PedidoPrestacaoServico ped){
//        RegistoPrestadorServicos rps = this.comp.getRegistoPrestadorServicos();
//        EnderecoPostal endPedido=ped.getEndP();
//        return rps.melhorPrestador(ped.getEndP(),ped.getHorarios());
//    }
//
//    /**
//     * Associa um pedido a um prestador
//     * @param pres
//     * @param ped
//     */
//    public void associarPedido(PrestadorServicos pres, PedidoPrestacaoServico ped){
//        pres.adicionarPedido(ped);
//    }
//}
