# PROYECTO_ED2

Proyecto Java de Estructura de Datos II desarrollado con NetBeans. Incluye una interfaz Swing para una tienda de comida y usa implementaciones de arboles y grafos.

## Requisitos

- Java JDK 8 o superior.
- NetBeans IDE, recomendado para abrir y ejecutar el proyecto facilmente.
- Apache Ant, solo si se quiere compilar desde consola con `build.xml`.

## Como descargar

```bash
git clone https://github.com/CondoriEstherD/Proyecto_ED2.git
cd Proyecto_ED2
```

## Como ejecutar en NetBeans

1. Abrir NetBeans.
2. Ir a `File > Open Project`.
3. Seleccionar la carpeta `Proyecto_ED2`.
4. Ejecutar el proyecto con `Run Project`.

La clase principal configurada es:

```text
proyecto_ed2.PROYECTO_ED2
```

## Como compilar desde consola

Si se tiene Apache Ant instalado:

```bash
ant clean jar
```

Luego ejecutar el archivo generado:

```bash
java -jar dist/PROYECTO_ED2.jar
```

## Estructura principal

```text
src/
  ARBOLES/       Implementaciones de arboles
  GRAFOS/        Implementaciones de grafos y Dijkstra
  proyecto_ed2/  Formularios Swing y clase principal
nbproject/       Configuracion compartible de NetBeans
build.xml        Script de compilacion de NetBeans/Ant
manifest.mf      Manifest del proyecto
```

## Notas

No se suben carpetas generadas como `build/` o `dist/`. Esas carpetas se crean nuevamente al compilar el proyecto.
