import builtins from 'rollup-plugin-node-builtins';
import babel from 'rollup-plugin-babel';
import typescript from 'rollup-plugin-typescript2'
import commonjs from 'rollup-plugin-commonjs'
// import external from 'rollup-plugin-peer-deps-external'
// import postcss from 'rollup-plugin-postcss-modules'
// import postcss from 'rollup-plugin-postcss'
import resolve from 'rollup-plugin-node-resolve'
// import url from 'rollup-plugin-url'
// import svgr from '@svgr/rollup'
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
    // babel({
    //   // presets: ['@babel/preset-env', '@babel/preset-react']
    //   // presets: [{"modules": false}, "es2015", ]
    // }),
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
      //   // mainFields: ['main', 'jsnext:main'],
        jsnext: true,
        main: true,
        modules: true
      }
    ),
    builtins(),
  ]
}
