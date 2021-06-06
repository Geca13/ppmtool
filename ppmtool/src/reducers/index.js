import { combineReducers } from 'redux'
import projectReducer from '../actions/projectReducer'
import backlogReducer from './backlogReducer'
import errorReducer from './errorReducer'

export default combineReducers({
    errors:errorReducer ,
    project: projectReducer,
    backlog:backlogReducer
})