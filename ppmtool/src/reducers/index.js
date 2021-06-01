import { combineReducers } from 'redux'
import projectReducer from '../actions/projectReducer'
import errorReducer from './errorReducer'

export default combineReducers({
    errors:errorReducer ,
    project: projectReducer
})