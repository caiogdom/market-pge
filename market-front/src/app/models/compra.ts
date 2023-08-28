export class Compra {
    id: string = '';
    cpfCliente: string = '';
    codigoProduto: string = '';
    dataCompra: Date = new Date();
    notaFiscal: Blob = new Blob();
}