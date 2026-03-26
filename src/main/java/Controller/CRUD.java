
package Controller;

import Model.nodo;


public class CRUD {

    nodo cab;

    //CREATE
    private nodo create(int id) {
        return new nodo(id);
    }

    private nodo insertl(int id) {
        nodo p = this.create(id);
        if (this.cab != null) {
            p.setSig(this.cab);
            this.cab.setAnt(p);
        }
        this.cab = p;
        return p;
    }
    
    //prueba de commit para git-hub
    public int caso1(){
        return 1;
    }

    public boolean insert(int id) {
        if (this.insertl(id) != null) {
            return true;
        }
        return false;
    }

    //READ
    public void showAll() {
        if (this.cab != null) {
            nodo p = this.cab;
            do {
                System.out.println("Ant: " + p.getAnt() + " id: " + p.getId() + " Sig: " + p.getSig());
                p = p.getSig();
            } while (p != null);
        }
    }

    private nodo select(int id) {
        if (this.cab != null) {
            nodo p = this.cab;
            do {
                if (p.getId() == id) {
                    return p;
                }
                p = p.getSig();
            } while (p != null);
        }
        return null;
    }

    public boolean update(int id, int nId) {
        nodo p = this.select(id);
        if (p != null) {
            p.setId(nId);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        nodo p = this.select(id);
        if (p != null) {
            if (p.getAnt() != null) {
                p.getAnt().setSig(p.getSig());
            } else {
                this.cab = p.getSig();
            }
            if (p.getSig() != null) {
                p.getSig().setAnt(p.getAnt());
            }
            return true;
        }
        return false;
    }

    private void exchange(nodo p, nodo q) {
        nodo s = null, z = null;
        if (p.getSig() != q) {
            s = p.getSig();
            z = q.getAnt();
            s.setAnt(q);
            z.setSig(p);
        }
        p.setSig(q.getSig());
        q.setAnt(p.getAnt());
        if (p.getAnt() != null) {
            p.getAnt().setSig(q);
        } else {
            this.cab = q;
        }
        if (q.getSig() != null) {
            q.getSig().setAnt(p);
        }
        if (s != null && z != null) {
            p.setAnt(z);
            q.setSig(s);
        } else {
            p.setAnt(q);
            q.setSig(p);
        }

    }

    //Metodo de ordenación radix sort
    public void radixSort() {
        if (this.cab == null || this.cab.getSig() == null) {
            return;
        }

        // 1. Encontrar el valor máximo para saber el número de dígitos
        int max = getMaximum();

        // 2. Iterar por cada posición de dígito (exp = 1, 10, 100...)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            distributeAndCollect(exp);
        }
    }

    private int getMaximum() {
        int max = cab.getId();
        nodo curr = cab;
        while (curr != null) {
            if (curr.getId() > max) {
                max = curr.getId();
            }
            curr = curr.getSig();
        }
        return max;
    }

    private void distributeAndCollect(int exp) {
        // Creamos 10 cubetas (pueden ser arreglos de nodos auxiliares)
        nodo[] heads = new nodo[10];
        nodo[] tails = new nodo[10];

        nodo curr = cab;
        while (curr != null) {
            int bucketIndex = (curr.getId() / exp) % 10;
            nodo nextNode = curr.getSig(); // Guardamos el siguiente antes de desconectar

            // Limpiamos punteros del nodo actual
            curr.setSig(null);
            curr.setAnt(null);

            // Insertar en la cubeta correspondiente
            if (heads[bucketIndex] == null) {
                heads[bucketIndex] = curr;
                tails[bucketIndex] = curr;
            } else {
                tails[bucketIndex].setSig(curr);
                curr.setAnt(tails[bucketIndex]);
                tails[bucketIndex] = curr;
            }
            curr = nextNode;
        }

        // Unir todas las cubetas para reconstruir la lista principal
        nodo newCab = null;
        nodo last = null;

        for (int i = 0; i < 10; i++) {
            if (heads[i] != null) {
                if (newCab == null) {
                    newCab = heads[i];
                } else {
                    last.setSig(heads[i]);
                    heads[i].setAnt(last);
                }
                last = tails[i];
            }
        }
        this.cab = newCab; // Actualizamos la cabeza
    }

}
