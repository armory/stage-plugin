import builtins from 'rollup-plugin-node-builtins';
import babel from 'rollup-plugin-babel';
import typescript from 'rollup-plugin-typescript2'
import commonjs from 'rollup-plugin-commonjs'
import resolve from 'rollup-plugin-node-resolve'
import replace from 'rollup-plugin-replace'

import pkg from './package.json'

export default {
  input: 'src/index.tsx',
  output: [
    {
      file: pkg.main,
      format: 'iife',
      exports: 'named',
      sourcemap: true,
      name: "MyStage"
    },
  ],
  plugins: [
    replace({
      'process.env.NODE_ENV': JSON.stringify( 'production' )
    }),
    typescript({
      rollupCommonJSResolveHack: true,
      clean: true
    }),
    commonjs(
      {
        include: ["./src/index.tsx", "node_modules/**",],
        namedExports: {
          'node_modules/react/index.js': ['Children', 'Component', 'PropTypes', 'createElement'],
        }
      }
    ),
    resolve(
      {
        jsnext: true,
        main: true,
        modules: true
      }
    ),
    builtins(),
  ]
}
