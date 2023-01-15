const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

module.exports = {
    entry: './src/index.ts',
    output: {
        filename: 'main.js',
        path: path.resolve(__dirname, 'dist'),
    },
    module: {
        rules: [{ test: /\.tsx?$/, use: 'ts-loader', exclude: /node_modules/ }],
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
    },
    plugins: [new HtmlWebpackPlugin({ template: 'src/index.html' })],
    devServer: {
        static: {
            directory: path.join(__dirname, 'public'),
        },
        compress: true,
        port: 9000,
    },
};
