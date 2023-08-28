import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteComponent } from './pages/cliente/cliente.component';
import { CompraComponent } from './pages/compra/compra.component';
import { ProdutoComponent } from './pages/produto/produto.component';

const routes: Routes = [
  {component: ClienteComponent, path: 'clientes'},
  {component: ProdutoComponent, path: 'produtos'},
  {component: CompraComponent, path: 'compras'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
