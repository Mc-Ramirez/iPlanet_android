
## Authors

- [@4prkali](https://github.com/4prkali)
- [@Mc-Ramirez](https://github.com/Mc-Ramirez)
- [@SergioRivera01](https://github.com/SergioRivera01)


# UD7_01 - Proyecto Integrador Android

Realizar una aplicación conectándoselo a una API propia
La aplicación tendrá que tener al menos las siguientes peticiones:

- Un GET que devuelva todos los elementos
- Un GET que devuelva un producto por su id
- Un GET para poder hacer un filtro por al menos 2 campos (podrá filtrar solo por uno pero tendrá que dar la posibilidad de al menos 2)
- Un PUT para añadir un elemento
- Un POST para actualizar un elemento Un DELETE para eliminar un elementon POST para actualizar un elemento
- Un DELETE para eliminar un elemento

La aplicación Android deberá al menos tener 3 ventanas:
- Ventana donde muestre todos los elementos, (y un elemento gráfico para poder filtrar)
- Ventana de detalle del elemento
- Ventana para añadir producto
- (Opcional) Ventana de filtro

Entregar una video de máximo 5 minutos, explicando el flujo de la aplicación y cada caso de uso
- Mostrar productos
- filtrar productos y ver los productos filtrados
- Añadir producto
- Modificar producto
- Eliminar producto
## Acknowledgements

 - [Awesome Readme Templates](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
 - [Awesome README](https://github.com/matiassingers/awesome-readme)
 - [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)


## API Reference

#### Get all items

```http
  GET /api/items
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.


## Appendix

Any additional information goes here


